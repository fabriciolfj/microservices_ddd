package br.com.spark.service.order.api.dto.response.mapper.payment;

import br.com.spark.service.order.api.dto.response.PaymentResponseDto;
import br.com.spark.service.order.domain.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentResponseMapper {

    @Mapping(source = "paypalPaymentId", target = "paypalPaymentId")
    @Mapping(source = "status", target = "status")
    PaymentResponseDto toDto(final Payment payment);
}
