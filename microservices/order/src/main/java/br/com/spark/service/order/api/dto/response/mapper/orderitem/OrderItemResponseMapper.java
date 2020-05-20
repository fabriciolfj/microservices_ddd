package br.com.spark.service.order.api.dto.response.mapper.orderitem;

import br.com.spark.service.order.api.dto.response.OrderItemResponseDto;
import br.com.spark.service.order.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemResponseMapper {

    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "id", target = "id")
    OrderItemResponseDto toDto(final OrderItem item);
}
