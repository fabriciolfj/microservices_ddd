package br.com.spark.service.customer.domain.service;

import br.com.spark.service.customer.domain.exceptions.CustomerNotFoundException;
import br.com.spark.service.customer.domain.model.Customer;
import br.com.spark.service.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer create(final Customer customer) {
        log.debug("Request to create Customer: {}", customer);
        customer.setEnabled(true);
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<Customer> findAll() {
        log.debug("Request to get all Customers");
        return this.customerRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public Customer findById(final Long id) {
        log.debug("Request to get Customer: {}", id);
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found. Id: " + id));
    }

    public void delete(final Long id) {
        log.debug("Request to delete Customer: {}", id);
        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));

        customer.setEnabled(false);
        this.customerRepository.save(customer);
    }
}
