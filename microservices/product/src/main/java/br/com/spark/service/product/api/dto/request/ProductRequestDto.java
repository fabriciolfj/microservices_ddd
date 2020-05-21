package br.com.spark.service.product.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {

    private String description;
    private BigDecimal price;
    private String status;
}
