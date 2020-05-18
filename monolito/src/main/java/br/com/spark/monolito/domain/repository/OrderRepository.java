package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
