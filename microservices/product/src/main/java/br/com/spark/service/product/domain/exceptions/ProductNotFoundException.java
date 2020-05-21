package br.com.spark.service.product.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    public ProductNotFoundException(final String msg) {
        super(msg);
    }
}
