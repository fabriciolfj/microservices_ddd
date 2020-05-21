package br.com.spark.service.order.domain.exceptions;

public class ProductClientException extends RuntimeException {

    public ProductClientException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ProductClientException(final String msg) {
        super(msg);
    }
}
