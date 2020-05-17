package br.com.spark.monolito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderItemDto {

    private Long id;
    private Long quantity;
    private Long productId;
    private Long orderId;
}
