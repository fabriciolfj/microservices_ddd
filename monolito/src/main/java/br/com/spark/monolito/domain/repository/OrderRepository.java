package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
