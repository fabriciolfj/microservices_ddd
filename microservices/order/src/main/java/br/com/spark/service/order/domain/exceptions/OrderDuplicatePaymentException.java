package br.com.spark.service.order.domain.exceptions;

public class OrderDuplicatePaymentException extends RuntimeException {

    public OrderDuplicatePaymentException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public OrderDuplicatePaymentException(final String msg) {
        super(msg);
    }
}
