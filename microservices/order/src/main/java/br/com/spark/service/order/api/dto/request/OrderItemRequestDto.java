package br.com.spark.service.order.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequestDto {

    @NotNull
    private Long productId;
    @Positive
    private Long quantity;
}
