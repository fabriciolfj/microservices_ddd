package br.com.spark.service.customer.api.mapper;

import br.com.spark.service.customer.api.dto.CustomerRequestDto;
import br.com.spark.service.customer.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerResquestMapper {


    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    Customer toDomain(final CustomerRequestDto dto);
}
