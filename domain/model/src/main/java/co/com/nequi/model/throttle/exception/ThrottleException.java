package co.com.nequi.model.throttle.exception;

import co.com.nequi.model.throttle.enums.TechnicalMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ThrottleException extends RuntimeException {

    private final TechnicalMessage technicalMessage;

    public ThrottleException(String message, TechnicalMessage technicalMessage) {
        super(message);
        this.technicalMessage = technicalMessage;
    }

    public ThrottleException(Throwable cause, TechnicalMessage technicalMessage) {
        super(cause);
        this.technicalMessage = technicalMessage;
    }
}
