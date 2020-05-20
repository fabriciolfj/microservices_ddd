package br.com.spark.service.order.domain.exceptions;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public PaymentNotFoundException(final String msg) {
        super(msg);
    }
}
