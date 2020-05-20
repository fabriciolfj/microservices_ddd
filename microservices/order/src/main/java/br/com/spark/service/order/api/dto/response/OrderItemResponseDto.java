package br.com.spark.service.order.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {

    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal price;
}
