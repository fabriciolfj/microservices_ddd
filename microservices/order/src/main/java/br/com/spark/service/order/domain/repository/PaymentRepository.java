package br.com.spark.service.order.domain.repository;

import br.com.spark.service.order.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
