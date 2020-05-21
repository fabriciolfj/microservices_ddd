package br.com.spark.service.order.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class OrderItemResponseDto {

    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal price;
}
