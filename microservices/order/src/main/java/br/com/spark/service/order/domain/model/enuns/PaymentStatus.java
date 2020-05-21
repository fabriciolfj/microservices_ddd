package br.com.spark.service.order.domain.model.enuns;

import br.com.spark.service.order.domain.exceptions.StatusPaymentInvalidException;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@ToString
public enum  PaymentStatus {

    ACCEPTED("accepted"), REFUSED("refused");

    private String describe;

    PaymentStatus(final String describe) {
        this.describe = describe;
    }

    public static PaymentStatus toEnum(final String describe) {
        return Stream.of(PaymentStatus.values())
                .filter(p -> p.getDescribe().equalsIgnoreCase(describe))
                .findFirst()
                .orElseThrow(() -> new StatusPaymentInvalidException("Payment invalid: " + describe));
    }
}
