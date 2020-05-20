package br.com.spark.service.order.domain.exceptions;

public class StatusPaymentInvalidException extends RuntimeException {

    public StatusPaymentInvalidException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public StatusPaymentInvalidException(final String msg) {
        super(msg);
    }
}
