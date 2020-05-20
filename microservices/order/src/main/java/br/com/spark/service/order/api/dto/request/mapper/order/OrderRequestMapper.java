package br.com.spark.service.order.api.dto.request.mapper.order;

import br.com.spark.service.order.api.dto.request.OrderRequestDto;
import br.com.spark.service.order.domain.model.Order;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderRequestMapperDecorator.class)
public interface OrderRequestMapper {

    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "customerId", target = "customerId")
    Order toDomain(final OrderRequestDto dto);

}
