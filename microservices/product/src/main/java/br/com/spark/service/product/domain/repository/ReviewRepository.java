package br.com.spark.service.product.domain.repository;

import br.com.spark.service.product.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
