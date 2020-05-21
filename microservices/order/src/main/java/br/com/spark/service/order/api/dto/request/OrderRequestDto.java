package br.com.spark.service.order.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private Long customerId;
    @NotEmpty
    private Set<OrderItemRequestDto> orderItems;
}
