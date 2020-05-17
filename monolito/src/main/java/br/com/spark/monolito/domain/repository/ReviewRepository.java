package br.com.spark.monolito.domain.repository;

import br.com.spark.monolito.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
