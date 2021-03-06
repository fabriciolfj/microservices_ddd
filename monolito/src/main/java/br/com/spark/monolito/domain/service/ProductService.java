package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.ProductDto;
import br.com.spark.monolito.domain.model.Product;
import br.com.spark.monolito.domain.model.enuns.ProductStatus;
import br.com.spark.monolito.domain.repository.CategoryRepository;
import br.com.spark.monolito.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> findAll() {
        log.debug("Request to get all Products");
        return this.productRepository.findAll().stream().map(ProductService::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        log.debug("Request to get Product : {}", id);
        return mapToDto(getProductDto(id));
    }

    public Product getProductDto(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public ProductDto create(ProductDto productDto) {
        log.debug("Request to create Product : {}", productDto);
        return mapToDto(this.productRepository.save(
                new Product(
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        productDto.getQuantity(),
                        ProductStatus.valueOf(productDto.getStatus()),
                        productDto.getSalesCounter(),
                        this.categoryRepository.findById(productDto.getCategory().getId())
                                .orElse(null), Collections.EMPTY_SET)));
    }

    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        this.productRepository.deleteById(id);
    }

    public static ProductDto mapToDto(Product product) {

        if (product != null) {
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getStatus().name(),
                    product.getSalesCounter(),
                    product.getReviews().stream()
                    .map(ReviewService::mapToDto).collect(Collectors.toSet()), CategoryService.mapToDto(product.getCategory()));
        }

        return null;
    }
}