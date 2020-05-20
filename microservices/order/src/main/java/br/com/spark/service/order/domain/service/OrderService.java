package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.api.dto.response.OrderResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.order.OrderResponseMapper;
import br.com.spark.service.order.domain.exceptions.OrderNotFoundException;
import br.com.spark.service.order.domain.model.Order;
import br.com.spark.service.order.domain.model.OrderItem;
import br.com.spark.service.order.domain.model.enuns.OrderStatus;
import br.com.spark.service.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderResponseMapper mapper;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<OrderResponseDto> findAll() {
        log.debug("Request to get all Orders");
        return this.orderRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public OrderResponseDto findById(Long id) {
        log.debug("Request to get Order : {}", id);
        return Optional.of(findEntity(id)).map(mapper::toDto).get();
    }

    public Order findEntity(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponseDto create(Order order) {
        log.debug("Request to create Order : {}", order);
        order.setStatus(OrderStatus.CREATION);
        order.setShipped(ZonedDateTime.now());

        orderRepository.save(order);
        return mapper.toDto(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
       try {
           this.orderRepository.deleteById(id);
       } catch (Exception e) { }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Order order) {
        orderRepository.save(order);
    }

    public OrderResponseDto addItem(OrderItem item, Long orderId) {
        var order = findEntity(orderId);

        if (order.getOrderItems().isEmpty()) {
            order.setOrderItems(new HashSet<>());
        }

        order.getOrderItems().add(item);
        order.updateTotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return mapper.toDto(orderRepository.save(order));
    }
}
