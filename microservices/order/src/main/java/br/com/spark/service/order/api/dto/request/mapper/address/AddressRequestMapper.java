package br.com.spark.service.order.api.dto.request.mapper.address;

import br.com.spark.service.order.api.dto.request.AddressRequestDto;
import br.com.spark.service.order.domain.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressRequestMapper {

    @Mapping(source = "address1", target = "address1")
    @Mapping(source = "address2", target = "address2")
    @Mapping(source = "postcode", target = "postcode")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "country", target = "country")
    Address toDomain(final AddressRequestDto dto);
}
