package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.api.dto.response.OrderItemResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.orderitem.OrderItemResponseMapper;
import br.com.spark.service.order.domain.exceptions.OrderItemNotFoundException;
import br.com.spark.service.order.domain.model.Order;
import br.com.spark.service.order.domain.model.OrderItem;
import br.com.spark.service.order.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemResponseMapper mapper;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<OrderItemResponseDto> findAll() {
        log.debug("Request to get all OrderItems");
        return this.orderItemRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderItemResponseDto findById(final Long id) {
        log.debug("Request to get OrderItem: {}", id);
        return this.orderItemRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new OrderItemNotFoundException("Order item not found: " + id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(final OrderItem orderItem) {
        log.debug("Request to create OrderItem : {}", orderItem);
        orderItemRepository.save(orderItem);
    }

    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        this.orderItemRepository.deleteById(id);
    }
}
