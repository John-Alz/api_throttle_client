package co.com.nequi.usecase.throttle;

import co.com.nequi.model.throttle.Throttle;
import co.com.nequi.model.throttle.gateways.ThrottleRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ThrottleUseCase {

    private final ThrottleRepository throttleRepository;

    public Mono<String> execute(Throttle throttle) {
        return throttleRepository.callServiceLambda(throttle);
    }

}
