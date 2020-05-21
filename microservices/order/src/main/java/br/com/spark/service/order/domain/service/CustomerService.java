package br.com.spark.service.order.domain.service;

import br.com.spark.service.order.domain.core.integration.client.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerServiceClient customerServiceClient;

    public void getCustomer(final Long id) {
        customerServiceClient.getCustomer(id);
    }
}
