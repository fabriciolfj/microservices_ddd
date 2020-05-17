package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
