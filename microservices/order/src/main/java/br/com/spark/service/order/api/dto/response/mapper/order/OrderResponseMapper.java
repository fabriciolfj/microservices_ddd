package br.com.spark.service.order.api.dto.response.mapper.order;

import br.com.spark.service.order.api.dto.response.OrderResponseDto;
import br.com.spark.service.order.domain.model.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderResponseMapperDecorator.class)
public interface OrderResponseMapper {

    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "id", target = "id")
    OrderResponseDto toDto(final Order order);
}

