package co.com.nequi.model.throttle.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INTERNAL_ERROR("500", "500", "Tuvimos un problema, estamos tratando de arreglarlo."),

    CALL_SERVICE_FAIL("429-001", "429", "Error al consumir lambda service, límite de solicitudes excedido.");

    private final String code;
    private final String externalCode;
    private final String message;


}
