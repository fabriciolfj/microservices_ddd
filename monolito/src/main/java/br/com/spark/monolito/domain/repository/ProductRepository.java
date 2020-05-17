package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
