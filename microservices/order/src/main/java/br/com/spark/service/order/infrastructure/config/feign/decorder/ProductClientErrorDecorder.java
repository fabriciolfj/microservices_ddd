package br.com.spark.service.order.infrastructure.config.feign.decorder;

import br.com.spark.service.order.domain.exceptions.ProductClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class ProductClientErrorDecorder implements ErrorDecoder {

    private ErrorDecoder delegate = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        final var statusCode = HttpStatus.valueOf(response.status());

        if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new ProductClientException("Falha ao consumir o servico");
        }

        return delegate.decode(methodKey, response);
    }
}
