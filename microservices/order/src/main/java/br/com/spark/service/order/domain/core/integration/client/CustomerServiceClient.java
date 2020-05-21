package br.com.spark.service.order.domain.core.integration.client;

import br.com.spark.service.order.domain.exceptions.CustomerFailConsumerException;
import br.com.spark.service.order.domain.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceClient {

    @Value("${customer.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    public void getCustomer(final Long id) {
        try {
            restTemplate.getForEntity(url + "/api/customers/" + id, String.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                if (((HttpClientErrorException) e).getStatusCode().is4xxClientError()) {
                    throw new CustomerNotFoundException("Customer not found: " + id);
                }
            }

            throw new CustomerFailConsumerException("Fail consumer customer service");
        }
    }
}
