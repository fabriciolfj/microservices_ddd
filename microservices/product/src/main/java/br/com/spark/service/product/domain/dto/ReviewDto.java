package br.com.spark.service.product.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private String title;
    private String description;
    private Long rating;
    private Long productId;
}
