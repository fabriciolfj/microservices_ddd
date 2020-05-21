package br.com.spark.service.product.infrastructure.config;

import br.com.spark.service.product.domain.model.Product;
import br.com.spark.service.product.domain.model.enuns.ProductStatus;
import br.com.spark.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitiationConfig implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        var prod1 = Product
                .builder()
                .description("Arroz")
                .price(BigDecimal.valueOf(19.00))
                .status(ProductStatus.ATIVE)
                .build();

        var prod2 = Product
                .builder()
                .description("Feijao")
                .price(BigDecimal.valueOf(16.99))
                .status(ProductStatus.ATIVE)
                .build();

        var prod3 = Product
                .builder()
                .description("Macarrao")
                .price(BigDecimal.valueOf(5.67))
                .status(ProductStatus.ATIVE)
                .build();

        var prod4 = Product
                .builder()
                .description("Farinha")
                .price(BigDecimal.valueOf(4.50))
                .status(ProductStatus.INATIVE)
                .build();

        repository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4));
    }
}
