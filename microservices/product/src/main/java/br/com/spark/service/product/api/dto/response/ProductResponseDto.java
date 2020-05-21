package br.com.spark.service.product.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private BigDecimal price;
}
