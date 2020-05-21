package br.com.spark.service.order.domain.core.integration.client;

import br.com.spark.service.order.domain.exceptions.CustomerFailConsumerException;
import br.com.spark.service.order.domain.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceClient {

    @Value("${customer.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    public void getCustomer(final Long id) {
        if(!request(id)) {
            throw new CustomerNotFoundException("Customer not found: " + id);
        };
    }

    private boolean request(Long id) {
        url = url + "/api/customers/" + id;
        try {
            return restTemplate.getForEntity(url, String.class).getStatusCode().is2xxSuccessful();
        } catch (Exception e ){
            throw new CustomerFailConsumerException("Fail consumer customer service");
        }
    }
}
