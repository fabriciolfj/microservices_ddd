package br.com.spark.service.order.api.dto.request.mapper.order;

import br.com.spark.service.order.api.dto.request.OrderItemRequestDto;
import br.com.spark.service.order.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemRequestMapper {

    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "productId", target = "productId")
    OrderItem toDomain(final OrderItemRequestDto dto);
}

