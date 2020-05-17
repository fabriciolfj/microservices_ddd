package br.com.spark.monolito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;


@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String status;
    private Integer salesCounter;
    private Set<ReviewDto> reviews;
    private CategoryDto category;
}
