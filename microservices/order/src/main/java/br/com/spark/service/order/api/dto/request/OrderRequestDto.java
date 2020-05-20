package br.com.spark.service.order.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class OrderRequestDto {

    private Long customerId;
    private BigDecimal totalPrice;
    private String status;
    private Set<OrderItemRequestDto> orderItems;
}
