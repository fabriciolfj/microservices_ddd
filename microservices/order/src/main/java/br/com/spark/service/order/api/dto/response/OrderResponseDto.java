package br.com.spark.service.order.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class OrderResponseDto {

    private Long id;
    @JsonProperty(value = "total_price")
    private BigDecimal totalPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate shipped;
    @JsonProperty(value = "customer_id")
    private String customerId;
    @JsonProperty(value = "order_items")
    private Set<OrderItemResponseDto> orderItems;
    private PaymentResponseDto payment;
    private AddressResponseDto address;
}
