package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Cart;
import br.com.spark.monolito.domain.model.enuns.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByStatus(CartStatus status);
    List<Cart> findByStatusAndCustomerId(CartStatus status, Long customerId);
}
