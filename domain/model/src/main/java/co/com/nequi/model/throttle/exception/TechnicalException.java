package co.com.nequi.model.throttle.exception;

import co.com.nequi.model.throttle.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class TechnicalException extends ThrottleException {

    public TechnicalException(TechnicalMessage technicalMessage) {
        super(technicalMessage);
    }

    public TechnicalException(String message, TechnicalMessage technicalMessage) {
        super(message, technicalMessage);
    }
}
