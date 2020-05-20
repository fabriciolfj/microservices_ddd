package br.com.spark.service.order.api.controller;

import br.com.spark.service.order.api.dto.request.PaymentRequestDto;
import br.com.spark.service.order.api.dto.request.mapper.payment.PaymentRequestMapper;
import br.com.spark.service.order.api.dto.response.PaymentResponseDto;
import br.com.spark.service.order.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.spark.service.order.infrastructure.web.Web.API;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRequestMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponseDto> findAll() {
        return this.paymentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentResponseDto findById(@PathVariable Long id) {
        return this.paymentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PaymentRequestDto paymentDto) {
        this.paymentService.create(mapper.toDomain(paymentDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.paymentService.delete(id);
    }
}
