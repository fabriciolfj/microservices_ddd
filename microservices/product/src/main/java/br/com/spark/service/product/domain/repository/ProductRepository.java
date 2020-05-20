package br.com.spark.service.product.domain.repository;

import br.com.spark.service.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
