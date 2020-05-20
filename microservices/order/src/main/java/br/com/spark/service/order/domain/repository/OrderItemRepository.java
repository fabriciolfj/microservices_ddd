package br.com.spark.service.order.domain.repository;

import br.com.spark.service.order.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
