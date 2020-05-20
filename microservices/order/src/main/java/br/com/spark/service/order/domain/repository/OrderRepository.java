package br.com.spark.service.order.domain.repository;

import br.com.spark.service.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
