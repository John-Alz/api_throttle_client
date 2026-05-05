package co.com.nequi.api;

import co.com.nequi.api.dto.error.APIErrorResponse;
import co.com.nequi.api.dto.error.ErrorDTO;
import co.com.nequi.api.dto.success.APISuccessResponse;
import co.com.nequi.api.dto.throttleDTO;
import co.com.nequi.api.mapper.ThrottleMapper;
import co.com.nequi.model.throttle.enums.TechnicalMessage;
import co.com.nequi.model.throttle.exception.TechnicalException;
import co.com.nequi.usecase.throttle.ThrottleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {

private  final ThrottleUseCase useCase;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        String messageId = "msg-" + UUID.randomUUID();
        return serverRequest.bodyToMono(throttleDTO.class)
                .map(ThrottleMapper.MAPPER::toModel)
                .flatMap(useCase::execute)
                .flatMap(result -> buildSuccessResponse(messageId, result, HttpStatus.OK))
                .onErrorResume(TechnicalException.class, error ->
                        buildErrorResponse(HttpStatus.TOO_MANY_REQUESTS, messageId,
                                ErrorDTO.builder()
                                        .code(TechnicalMessage.CALL_SERVICE_FAIL.getCode())
                                        .message(TechnicalMessage.CALL_SERVICE_FAIL.getMessage())
                                        .build()
                        )
                )
                .onErrorResume(error -> buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, messageId,
                        ErrorDTO.builder()
                                .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                .message(TechnicalMessage.INTERNAL_ERROR.getMessage())
                                .build()
                ));
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus status, String messageId, ErrorDTO error) {
        return Mono.defer(() -> {
            APIErrorResponse apiErrorResponse = APIErrorResponse
                    .builder()
                    .messageId(messageId)
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .error(error)
                    .build();
            return ServerResponse.status(status)
                    .bodyValue(apiErrorResponse);
        });
    }

    private <T> Mono<ServerResponse> buildSuccessResponse(String messageId, T data,
                                                          HttpStatus status) {
        APISuccessResponse apiSuccessResponse = APISuccessResponse.<T>builder()
                .messageId(messageId)
                .data(data)
                .build();
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(apiSuccessResponse);
    }

}
