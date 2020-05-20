package br.com.spark.service.order.api.dto.request.mapper.payment;

import br.com.spark.service.order.api.dto.request.PaymentRequestDto;
import br.com.spark.service.order.domain.model.Payment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(PaymentRequestMapperDecorator.class)
public interface PaymentRequestMapper {

    @Mapping(source = "paypalPaymentId", target = "paypalPaymentId")
    @Mapping(source = "status", target = "status")
    Payment toDomain(final PaymentRequestDto dto);
}
