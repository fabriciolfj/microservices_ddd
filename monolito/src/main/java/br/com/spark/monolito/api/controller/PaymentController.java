package br.com.spark.monolito.api.controller;

import br.com.spark.monolito.domain.dto.PaymentDto;
import br.com.spark.monolito.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.spark.monolito.infrastructure.web.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentDto> findAll() {
        return this.paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentDto findById(@PathVariable Long id) {
        return this.paymentService.findById(id);
    }

    @PostMapping
    public PaymentDto create(@RequestBody PaymentDto paymentDto) {
        return this.paymentService.create(paymentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.paymentService.delete(id);
    }
}
