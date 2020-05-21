package br.com.spark.service.product.api.dto.response;

import br.com.spark.service.product.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResponseDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "price", target = "price")
    ProductResponseDto toDto(final Product product);
}
