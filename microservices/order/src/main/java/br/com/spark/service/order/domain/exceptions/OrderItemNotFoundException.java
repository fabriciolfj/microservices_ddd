package br.com.spark.service.order.domain.exceptions;

public class OrderItemNotFoundException extends RuntimeException {

    public OrderItemNotFoundException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public OrderItemNotFoundException(final String msg) {
        super(msg);
    }
}
