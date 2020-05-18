package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.OrderAddressDto;
import br.com.spark.monolito.domain.dto.OrderDto;
import br.com.spark.monolito.domain.model.Address;
import br.com.spark.monolito.domain.model.Cart;
import br.com.spark.monolito.domain.model.Order;
import br.com.spark.monolito.domain.model.enuns.OrderStatus;
import br.com.spark.monolito.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> findAll() {
        log.debug("Request to get all Orders");
        return this.orderRepository.findAll().stream().map(OrderService::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        log.debug("Request to get Order : {}", id);
        return this.orderRepository.findById(id).map(OrderService::mapToDto).orElse(null);
    }

    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order : {}", orderDto);
        return mapToDto(this.orderRepository.save(new Order(BigDecimal.ZERO, OrderStatus.CREATION, null, null, null, Collections.emptySet())));
    }

    public Order create() {
        log.debug("Request to create Order");
        return this.orderRepository.save(new Order(BigDecimal.ZERO, OrderStatus.CREATION, null, null, null, Collections.emptySet()));
    }

    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        this.orderRepository.deleteById(id);
    }

    public static OrderDto mapToDto(Order order) {
        if (order != null) {
            return new OrderDto(order.getId(),
                    order.getTotalPrice(),
                    order.getStatus().name(),
                    order.getShipped(),
                    PaymentService.mapToDto(order.getPayment()),
                    AddressService.mapToDto(order.getShipmentAddress()),
                    order.getOrderItems().stream().map(OrderItemService::mapToDto).collect(Collectors.toSet()));
        }

        return null;
    }

    public void update(Long id, OrderAddressDto dto) {
        orderRepository.findById(id)
                .map(o -> {
                    var address = new Address(dto.getAddress1(), dto.getAddress2(), dto.getCity(), dto.getPostCode(), dto.getCountry());
                    o.setShipmentAddress(address);
                    o.setShipped(ZonedDateTime.now());
                    return orderRepository.save(o);
                })
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
