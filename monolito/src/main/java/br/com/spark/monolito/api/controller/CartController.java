package br.com.spark.monolito.api.controller;

import br.com.spark.monolito.domain.dto.CartDto;
import br.com.spark.monolito.domain.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.spark.monolito.infrastructure.web.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartDto> findAll() {
        return this.cartService.findAll();
    }

    @GetMapping("/active")
    public List<CartDto> findAllActiveCarts() {
        return this.cartService.findAllActiveCarts();
    }

    @GetMapping("/customer/{id}")
    public CartDto getActiveCartForCustomer(@PathVariable("id") final Long customerId) {
        return this.cartService.getActiveCart(customerId);
    }

    @GetMapping("/{id}")
    public CartDto findById(@PathVariable final Long id) {
        return this.cartService.findById(id);
    }

    @PostMapping("/customer/{id}")
    public CartDto create(@PathVariable("id") final Long customerId) {
        return this.cartService.create(customerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.cartService.delete(id);
    }
}
