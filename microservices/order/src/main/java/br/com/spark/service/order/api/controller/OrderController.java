package br.com.spark.service.order.api.controller;

import br.com.spark.service.order.api.dto.request.AddressRequestDto;
import br.com.spark.service.order.api.dto.request.OrderItemRequestDto;
import br.com.spark.service.order.api.dto.request.OrderRequestDto;
import br.com.spark.service.order.api.dto.request.mapper.address.AddressRequestMapper;
import br.com.spark.service.order.api.dto.request.mapper.order.OrderItemRequestMapper;
import br.com.spark.service.order.api.dto.request.mapper.order.OrderRequestMapper;
import br.com.spark.service.order.api.dto.response.OrderResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.address.AddressResponseMapper;
import br.com.spark.service.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

import static br.com.spark.service.order.infrastructure.web.Web.API;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderRequestMapper mapper;
    private final OrderItemRequestMapper itemMapper;
    private final AddressRequestMapper addressRequestMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto findById(@PathVariable Long id) {
        return this.orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.orderService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@Valid @RequestBody OrderRequestDto dto) {
        var order = mapper.toDomain(dto);
        return orderService.create(order);
    }

    @PutMapping("/{id}/item")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderResponseDto addItem(@Valid @RequestBody OrderItemRequestDto dto, @PathVariable("id") Long orderId) {
        var item = itemMapper.toDomain(dto);
        return orderService.addItem(item, orderId);
    }

    @PutMapping("/{id}/address")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderResponseDto addAddress(@Valid @RequestBody AddressRequestDto dto, @PathVariable("id") Long orderId) {
        var address = addressRequestMapper.toDomain(dto);
        return orderService.updateAddress(address, orderId);
    }
}
