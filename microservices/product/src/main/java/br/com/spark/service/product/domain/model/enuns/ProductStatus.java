package br.com.spark.service.product.domain.model.enuns;

import br.com.spark.service.product.domain.exceptions.StatusInvalidException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ProductStatus {
    ATIVE("ative"), INATIVE("inative");

    private String describe;

    ProductStatus(final String describe) {
        this.describe = describe;
    }

    public static ProductStatus toEnum(final String describe) {
        return Stream.of(ProductStatus.values())
                .filter(p -> p.getDescribe().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(() -> new StatusInvalidException("Status invalid: " + describe));
    }
}
