package br.com.spark.service.order.api.controller;

import br.com.spark.service.order.api.dto.request.PaymentRequestDto;
import br.com.spark.service.order.api.dto.request.mapper.payment.PaymentRequestMapper;
import br.com.spark.service.order.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentRequestMapper mapper;

    @PostMapping("/{id}/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PaymentRequestDto paymentDto, @PathVariable("id") Long orderId) {
        this.paymentService.create(mapper.toDomain(paymentDto), orderId);
    }
}
