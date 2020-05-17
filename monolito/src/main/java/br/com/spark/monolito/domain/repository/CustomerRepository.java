package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByEnabled(Boolean enabled);
}
