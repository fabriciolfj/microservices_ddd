package br.com.spark.service.order.domain.exceptions;

public class StatusCartInvalidException extends RuntimeException {

    public StatusCartInvalidException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public StatusCartInvalidException(final String msg) {
        super(msg);
    }
}
