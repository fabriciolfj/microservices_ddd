package br.com.spark.service.order.api.dto.request.mapper.payment;

import br.com.spark.service.order.api.dto.request.PaymentRequestDto;
import br.com.spark.service.order.domain.model.Payment;
import br.com.spark.service.order.domain.model.enuns.PaymentStatus;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class PaymentRequestMapperDecorator implements PaymentRequestMapper {

    @Autowired
    private PaymentRequestMapper mapper;

    public Payment toDomain(final PaymentRequestDto dto) {
        var payment = mapper.toDomain(dto);
        payment.setStatus(PaymentStatus.toEnum(dto.getStatus()));
        return payment;
    }
}
