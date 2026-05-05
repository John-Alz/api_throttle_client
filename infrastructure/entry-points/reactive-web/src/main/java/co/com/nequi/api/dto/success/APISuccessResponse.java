package co.com.nequi.api.dto.success;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APISuccessResponse<T> {

    private String messageId;
    private T data;

}
