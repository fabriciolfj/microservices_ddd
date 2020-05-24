package br.com.spark.service.order.domain.core.integration.client;

import br.com.spark.service.order.domain.core.integration.client.dto.ProductResponseDto;
import br.com.spark.service.order.infrastructure.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "http://customer", decode404 = true, configuration = FeignConfiguration.class)
public interface CustomerServiceClient {

    @GetMapping(value = "/customers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductResponseDto getCustomer(@PathVariable final Long id);
}
