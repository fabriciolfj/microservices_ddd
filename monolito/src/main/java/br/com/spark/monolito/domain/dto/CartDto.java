package br.com.spark.monolito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {

    private Long id;
    private Long orderId;
    private CustomerDto customerDto;
    private String status;
}
