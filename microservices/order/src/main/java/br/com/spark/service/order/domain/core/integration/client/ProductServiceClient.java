package br.com.spark.service.order.domain.core.integration.client;

import br.com.spark.service.order.domain.core.integration.client.dto.ProductResponseDto;
import br.com.spark.service.order.infrastructure.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "http://products:8080", name = "product", decode404 = true, configuration = FeignConfiguration.class)
public interface ProductServiceClient {

    @GetMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductResponseDto findByProduct(@PathVariable final Long id);
}
