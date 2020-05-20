package br.com.spark.service.order.api.dto.response.mapper.address;


import br.com.spark.service.order.api.dto.response.AddressResponseDto;
import br.com.spark.service.order.domain.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    AddressResponseDto toDto(final Address address);
}
