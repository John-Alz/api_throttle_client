package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.throttleDTO;
import co.com.nequi.model.throttle.Throttle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThrottleMapper {

    ThrottleMapper MAPPER = Mappers.getMapper(ThrottleMapper.class);

    Throttle toModel(throttleDTO throttleDTO);

}
