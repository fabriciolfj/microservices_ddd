package br.com.spark.service.order.api.dto.request.mapper.order;

import br.com.spark.service.order.api.dto.request.OrderItemRequestDto;
import br.com.spark.service.order.api.dto.request.OrderRequestDto;
import br.com.spark.service.order.domain.model.Order;
import br.com.spark.service.order.domain.model.OrderItem;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public abstract class OrderRequestMapperDecorator implements OrderRequestMapper {

    @Autowired
    private OrderRequestMapper mapper;

    @Override
    public Order toDomain(OrderRequestDto dto) {
        var order = mapper.toDomain(dto);
        convertOrderItemDtoToDomain(dto.getOrderItems(), order).forEach(item -> order.addItens(item));
        return order;
    }

    private Set<OrderItem> convertOrderItemDtoToDomain(final Set<OrderItemRequestDto> dtos, final Order order) {
        return dtos.stream().map(
                orderItem -> OrderItem.builder()
                                .order(order)
                                .productId(orderItem.getProductId())
                                .price(orderItem.getPrice())
                                .quantity(orderItem.getQuantity())
                            .build())
                .collect(Collectors.toSet());
    }
}
