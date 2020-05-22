package br.com.spark.service.customer.api.controller;

import br.com.spark.service.customer.api.dto.CustomerRequestDto;
import br.com.spark.service.customer.api.dto.CustomerResponseDto;
import br.com.spark.service.customer.api.mapper.CustomerResponseMapper;
import br.com.spark.service.customer.api.mapper.CustomerResquestMapper;
import br.com.spark.service.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerResquestMapper resquestMapper;
    private final CustomerResponseMapper responseMapper;

    @GetMapping
    public List<CustomerResponseDto> findAll() {
        return customerService
                .findAll()
                .stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CustomerResponseDto findById(@PathVariable Long id) {
        return Optional.of(this.customerService.findById(id))
                .map(responseMapper::toDto).get();
    }

    @PostMapping
    public CustomerResponseDto create(@RequestBody final CustomerRequestDto customerDto) {
        return responseMapper.toDto(customerService.create(resquestMapper.toDomain(customerDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.customerService.delete(id);
    }
}
