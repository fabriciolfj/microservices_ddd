package br.com.spark.monolito.api.controller;

import br.com.spark.monolito.domain.dto.OrderDto;
import br.com.spark.monolito.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.spark.monolito.infrastructure.web.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long id) {
        return this.orderService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.orderService.delete(id);
    }
}
