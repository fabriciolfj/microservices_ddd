package br.com.spark.service.order.domain.exceptions;

public class StatusOrderInvalidException extends RuntimeException {

    public StatusOrderInvalidException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public StatusOrderInvalidException(final String msg) {
        super(msg);
    }
}
