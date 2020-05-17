package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.OrderItemDto;
import br.com.spark.monolito.domain.model.Order;
import br.com.spark.monolito.domain.model.OrderItem;
import br.com.spark.monolito.domain.model.Product;
import br.com.spark.monolito.domain.repository.OrderItemRepository;
import br.com.spark.monolito.domain.repository.OrderRepository;
import br.com.spark.monolito.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public List<OrderItemDto> findAll() {
        log.debug("Request to get all OrderItems");
        return this.orderItemRepository.findAll()
                .stream()
                .map(OrderItemService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderItemDto findById(final Long id) {
        log.debug("Request to get OrderItem: {}", id);
        return this.orderItemRepository.findById(id)
                .map(OrderItemService::mapToDto)
                .orElse(null);
    }

    public OrderItemDto create(final OrderItemDto orderItemDto) {
        log.debug("Request to create OrderItem : {}", orderItemDto);
        Order order = this.orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(() -> new IllegalStateException("The Order does not exist!"));
        Product product = this.productRepository.findById(orderItemDto.getProductId()).orElseThrow(()  ->
        new IllegalStateException("The Product does not exist!"));
        return mapToDto(this.orderItemRepository.save(new OrderItem(orderItemDto.getQuantity(), product, order)));
    }

    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        this.orderItemRepository.deleteById(id);
    }

    public static OrderItemDto mapToDto(OrderItem orderItem) {
        return ofNullable(orderItem)
                .map(o -> new OrderItemDto(o.getId(), o.getQuantity(), o.getProduct().getId(), o.getOrder().getId()))
                .orElse(null);
    }
}
