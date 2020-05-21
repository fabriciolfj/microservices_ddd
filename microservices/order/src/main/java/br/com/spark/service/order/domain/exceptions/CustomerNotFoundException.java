package br.com.spark.service.order.domain.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public CustomerNotFoundException(final String msg) {
        super(msg);
    }
}
