package br.com.spark.monolito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private Set<ProductDto> products;
}
