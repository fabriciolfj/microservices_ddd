package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.api.dto.response.OrderResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.order.OrderResponseMapper;
import br.com.spark.service.order.domain.core.integration.client.ProductServiceClient;
import br.com.spark.service.order.domain.exceptions.OrderDuplicatePaymentException;
import br.com.spark.service.order.domain.exceptions.OrderNotFoundException;
import br.com.spark.service.order.domain.model.Address;
import br.com.spark.service.order.domain.model.Order;
import br.com.spark.service.order.domain.model.OrderItem;
import br.com.spark.service.order.domain.model.Payment;
import br.com.spark.service.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderResponseMapper mapper;
    private final OrderItemService orderItemService;
    private final ProductServiceClient productServiceClient;

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
    public OrderResponseDto create(final Order order) {
        log.debug("Request to create Order : {}", order);
        order.setShipped(LocalDate.now());
        order.getOrderItems().stream().forEach(this::getPriceItem);
        order.updateTotal();
        orderRepository.save(order);
        return mapper.toDto(order);
    }

    private OrderItem getPriceItem(final OrderItem item) {
        var dto = productServiceClient.findByProduct(item.getProductId());
        item.setPrice(dto.getPrice());
        return item;
    }

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

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponseDto addItem(OrderItem item, final Long orderId) {
        var order = findEntity(orderId);

        item = getPriceItem(item);
        addItens(item, order);

        order.updateTotal();
        orderRepository.save(order);
        orderRepository.flush();
        return mapper.toDto(order);
    }

    public void addItens(OrderItem orderItem, Order order) {
        order.getOrderItems().stream()
                .filter(i -> i.getProductId().equals(orderItem.getProductId()))
                .findAny()
                .ifPresentOrElse(p -> {
                    p.setQuantity(p.getQuantity() + orderItem.getQuantity());
                    p.setPrice(orderItem.getPrice());
                }, () -> {
                    orderItem.setOrder(order);
                    order.getOrderItems().add(orderItem);
                    orderItemService.create(orderItem);
                });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponseDto updateAddress(final Address address, final Long oderId) {
        var order = findEntity(oderId);
        order.setShipmentAddress(address);
        orderRepository.save(order);
        return mapper.toDto(order);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePayment(Payment payment, Long orderId) {
        var order = findEntity(orderId);

        if (ofNullable(order.getPayment()).isPresent()) {
            throw new OrderDuplicatePaymentException("Order exists payment.Order: " + orderId);
        }

        order.setPayment(payment);
        orderRepository.save(order);
        orderRepository.flush();
    }
}
