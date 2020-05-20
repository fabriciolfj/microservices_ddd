package br.com.spark.service.product.domain.repository;

import br.com.spark.service.product.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
