package br.com.spark.service.product.api.dto.request;

import br.com.spark.service.product.domain.model.Product;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(ProductRequestMapperDecorator.class)
public interface ProductRequestMapper {

    @Mapping(source = "status", target = "status", ignore = true)
    Product toDomain(final ProductRequestDto dto);
}
