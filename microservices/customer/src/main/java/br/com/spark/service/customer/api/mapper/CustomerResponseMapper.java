package br.com.spark.service.customer.api.mapper;

import br.com.spark.service.customer.api.dto.CustomerResponseDto;
import br.com.spark.service.customer.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    @Mapping(source = "id", target = "id")
    CustomerResponseDto toDto(final Customer customer);
}
