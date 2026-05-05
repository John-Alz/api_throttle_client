package co.com.nequi.consumer;

import co.com.nequi.model.throttle.Throttle;
import co.com.nequi.model.throttle.enums.TechnicalMessage;
import co.com.nequi.model.throttle.exception.TechnicalException;
import co.com.nequi.model.throttle.gateways.ThrottleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThrottleRestConsumer implements ThrottleRepository{
    private final WebClient client;

    @Override
    public Mono<String> callServiceLambda(Throttle throttle) {
        return client
                .get()
                .uri("/api/reto-apgtw" )
                .retrieve()
                .onStatus(
                        status -> status.value() == 429,
                        response -> Mono.error(new TechnicalException(TechnicalMessage.CALL_SERVICE_FAIL))
                )
                .bodyToMono(String.class);
    }
}
