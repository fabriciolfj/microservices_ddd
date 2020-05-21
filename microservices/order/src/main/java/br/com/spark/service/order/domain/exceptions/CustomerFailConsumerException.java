package br.com.spark.service.order.domain.exceptions;

public class CustomerFailConsumerException extends RuntimeException {

    public CustomerFailConsumerException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public CustomerFailConsumerException(final String msg) {
        super(msg);
    }
}
