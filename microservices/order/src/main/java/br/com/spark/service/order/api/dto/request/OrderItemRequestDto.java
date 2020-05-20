package br.com.spark.service.order.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequestDto {

    private Long productId;
    private Long quantity;
    private BigDecimal price;
}
