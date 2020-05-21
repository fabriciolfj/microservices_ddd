package br.com.spark.service.order.domain.core.integration.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private BigDecimal price;
}
