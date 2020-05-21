package br.com.spark.service.product.domain.exceptions;

public class StatusInvalidException extends RuntimeException {

    public StatusInvalidException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public StatusInvalidException(final String msg) {
        super(msg);
    }
}
