package br.com.spark.service.customer.domain.repository;

import br.com.spark.service.customer.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
