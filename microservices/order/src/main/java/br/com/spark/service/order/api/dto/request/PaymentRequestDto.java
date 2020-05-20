package br.com.spark.service.order.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestDto {

    private String paypalPaymentId;
    private String status;
    private Long orderId;
}
