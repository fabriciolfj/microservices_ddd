package br.com.spark.monolito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private String paypalPaymentId;
    private String status;
    private Long orderId;
}
