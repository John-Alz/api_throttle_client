package co.com.nequi.model.throttle.gateways;

import co.com.nequi.model.throttle.Throttle;
import reactor.core.publisher.Mono;

public interface ThrottleRepository {

    Mono<String> callServiceLambda(Throttle throttle);

}
