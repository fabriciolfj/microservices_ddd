package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
