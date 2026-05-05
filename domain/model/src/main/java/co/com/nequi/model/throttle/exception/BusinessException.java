package co.com.nequi.model.throttle.exception;

import co.com.nequi.model.throttle.enums.TechnicalMessage;
import lombok.Getter;

@Getter
public class BusinessException extends ThrottleException {

    public BusinessException(TechnicalMessage technicalMessage) {
        super(technicalMessage);
    }

    public BusinessException(String message, TechnicalMessage technicalMessage) {
        super(message, technicalMessage);
    }
}
