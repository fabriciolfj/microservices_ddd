package br.com.spark.service.order.domain.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public OrderNotFoundException(final String msg) {
        super(msg);
    }
}
