package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.domain.core.integration.client.ProductServiceClient;
import br.com.spark.service.order.domain.core.integration.client.dto.ProductResponseDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductServiceClient client;

    @HystrixCommand(fallbackMethod = "fallBack")
    public ProductResponseDto getProduct(final Long id) {
        return client.findByProduct(id);
    }

    public ProductResponseDto fallBack(final Long id) {
        return ProductResponseDto.builder()
                .id(99L)
                .price(BigDecimal.valueOf(99.99))
                .build();
    }

}
