package br.com.spark.service.customer.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private String name;
    private String email;
    private String telephone;
}
