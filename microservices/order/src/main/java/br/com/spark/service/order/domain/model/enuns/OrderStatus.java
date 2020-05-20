package br.com.spark.service.order.domain.model.enuns;

import br.com.spark.service.order.domain.exceptions.StatusOrderInvalidException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum OrderStatus {

    CREATION("creation"), PROCESSED("processed"), PENDING("pending"), CANCELED("canceled");

    private String describe;

    OrderStatus(final String describe) {
        this.describe = describe;
    }

    public static OrderStatus toEnum(final String describe) {
        return Stream.of(OrderStatus.values())
                .filter(o -> o.getDescribe().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(() -> new StatusOrderInvalidException("Status not found: " + describe));
    }

}
