package br.com.spark.service.order.api.dto.response.mapper.order;

import br.com.spark.service.order.api.dto.response.OrderResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.address.AddressResponseMapper;
import br.com.spark.service.order.api.dto.response.mapper.orderitem.OrderItemResponseMapper;
import br.com.spark.service.order.api.dto.response.mapper.payment.PaymentResponseMapper;
import br.com.spark.service.order.domain.model.Order;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@NoArgsConstructor
public abstract class OrderResponseMapperDecorator implements OrderResponseMapper {

    @Autowired
    private OrderResponseMapper mapper;
    @Autowired
    private PaymentResponseMapper paymentResponseMapper;
    @Autowired
    private AddressResponseMapper addressResponseMapper;
    @Autowired
    private OrderItemResponseMapper orderItemResponseMapper;

    @Override
    public OrderResponseDto toDto(final Order entity) {
        var order = mapper.toDto(entity);
        order.setPayment(paymentResponseMapper.toDto(entity.getPayment()));
        order.setOrderItems(entity
                .getOrderItems()
                .stream().map(o -> orderItemResponseMapper.toDto(o)).collect(Collectors.toSet()));
        order.setAddress(addressResponseMapper.toDto(entity.getShipmentAddress()));
        order.setShipped(entity.getShipped());
        return order;
    }
}
