package br.com.spark.service.customer.domain.service;

import br.com.spark.service.customer.api.dto.CustomerDto;
import br.com.spark.service.customer.domain.model.Customer;
import br.com.spark.service.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto create(final CustomerDto customerDto) {
        log.debug("Request to create Customer: {}", customerDto);
        return maptoDto(this.customerRepository.save(
                new Customer(
                        null,
                        customerDto.getName(),
                        customerDto.getEmail(),
                        customerDto.getTelephone(),
                        Boolean.TRUE)
        ));
    }

    public List<CustomerDto> findAll() {
        log.debug("Request to get all Customers");
        return this.customerRepository.findAll()
                .stream()
                .map(CustomerService::maptoDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDto findById(final Long id) {
        log.debug("Request to get Customer: {}", id);
        return this.customerRepository.findById(id)
                .map(CustomerService::maptoDto)
                .orElse(null);
    }

    public List<CustomerDto> findAllActive() {
        log.debug("Request to get all Customers");
        return this.customerRepository.findAllByEnabled(true)
                .stream()
                .map(CustomerService::maptoDto)
                .collect(Collectors.toList());
    }

    public List<CustomerDto> findAllInactive() {
        log.debug("Request to get all Customers");
        return this.customerRepository.findAllByEnabled(false)
                .stream()
                .map(CustomerService::maptoDto)
                .collect(Collectors.toList());
    }

    public void delete(final Long id) {
        log.debug("Request to delete Customer: {}", id);
        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Customer with id " + id));

        customer.setEnabled(false);
        this.customerRepository.save(customer);
    }

    public static CustomerDto maptoDto(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> new CustomerDto(c.getId(), c.getName(), c.getEmail(), c.getTelephone()))
                .orElse(null);
    }
}
