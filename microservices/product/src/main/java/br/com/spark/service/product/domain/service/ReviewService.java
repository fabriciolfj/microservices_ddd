package br.com.spark.service.product.domain.service;

import br.com.spark.service.product.domain.dto.ReviewDto;
import br.com.spark.service.product.domain.model.Review;
import br.com.spark.service.product.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public List<ReviewDto> findAll() {
        log.debug("Request to get all Reviews");
        return this.reviewRepository.findAll().stream().map(ReviewService::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDto findById(Long id) {
        log.debug("Request to get Review : {}", id);
        return this.reviewRepository.findById(id).map(ReviewService::mapToDto).orElse(null);
    }

    public ReviewDto create(ReviewDto reviewDto) {
        log.debug("Request to create Review : {}", reviewDto);
        return mapToDto(this.reviewRepository.save(new Review(null, reviewDto.getTitle(), reviewDto.getDescription(), reviewDto.getRating(),
                productService.getProductDto(reviewDto.getProductId()))));
    }

    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        this.reviewRepository.deleteById(id);
    }

    public static ReviewDto mapToDto(Review review) {
        if (review != null) {
            return new ReviewDto(review.getId(), review.getTitle(), review.getDescription(), review.getRating(),null);
        }
        return null;
    }
}