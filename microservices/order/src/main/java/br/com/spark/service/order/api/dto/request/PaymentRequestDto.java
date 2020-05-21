package br.com.spark.service.order.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private String paypalPaymentId;
    private String status;
}
