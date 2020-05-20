package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.api.dto.response.PaymentResponseDto;
import br.com.spark.service.order.api.dto.response.mapper.payment.PaymentResponseMapper;
import br.com.spark.service.order.domain.exceptions.PaymentNotFoundException;
import br.com.spark.service.order.domain.model.Payment;
import br.com.spark.service.order.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentResponseMapper mapper;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<PaymentResponseDto> findAll() {
        log.debug("Request to get all Payments");
        return this.paymentRepository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PaymentResponseDto findById(Long id) {
        log.debug("Request to get Payment : {}", id);
        return this.paymentRepository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found: " + id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(Payment payment) {
        log.debug("Request to create Payment : {}", payment);
        this.paymentRepository.save(payment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        log.debug("Request to delete Payment : {}", id);
        try {
            this.paymentRepository.deleteById(id);
        } catch (Exception e) { }
    }
}