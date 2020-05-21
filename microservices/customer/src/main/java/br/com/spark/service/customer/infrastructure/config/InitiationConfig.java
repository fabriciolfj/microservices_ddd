package br.com.spark.service.customer.infrastructure.config;

import br.com.spark.service.customer.domain.model.Customer;
import br.com.spark.service.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class InitiationConfig implements CommandLineRunner {

    private final CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        var customer1 = Customer
                .builder()
                .email("teste@gmail.com")
                .enabled(true)
                .name("teste1")
                .build();

        var customer2 = Customer
                .builder()
                .email("teste2@gmail.com")
                .enabled(true)
                .name("teste2")
                .build();

        repository.saveAll(Arrays.asList(customer1, customer2));

    }
}
