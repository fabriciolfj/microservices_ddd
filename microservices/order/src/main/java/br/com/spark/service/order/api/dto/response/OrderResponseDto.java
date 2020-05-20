package br.com.spark.service.order.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;
    @JsonProperty(value = "total_price")
    private BigDecimal totalPrice;
    private String status;
    private String shipped;
    @JsonProperty(value = "customer_id")
    private String customerId;
    @JsonProperty(value = "order_items")
    private Set<OrderItemResponseDto> orderItems;
    private PaymentResponseDto payment;
    private AddressResponseDto address;
}
