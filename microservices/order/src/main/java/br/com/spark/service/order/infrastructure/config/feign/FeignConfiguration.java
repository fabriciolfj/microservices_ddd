package br.com.spark.service.order.infrastructure.config.feign;

import br.com.spark.service.order.domain.core.integration.client.ProductServiceClient;
import br.com.spark.service.order.infrastructure.config.feign.decorder.ProductClientErrorDecorder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = ProductServiceClient.class)
public class FeignConfiguration {

    @Bean
    public ProductClientErrorDecorder productClientErrorDecorder() {
        return new ProductClientErrorDecorder();
    }
}
