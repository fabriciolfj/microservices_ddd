package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
