package br.com.spark.service.product.domain.service;

import br.com.spark.service.product.api.dto.request.ProductRequestDto;
import br.com.spark.service.product.api.dto.response.ProductResponseDto;
import br.com.spark.service.product.api.dto.response.ProductResponseDtoMapper;
import br.com.spark.service.product.domain.exceptions.ProductNotFoundException;
import br.com.spark.service.product.domain.model.Product;
import br.com.spark.service.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseDtoMapper mappper;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<ProductResponseDto> findAll() {
        log.debug("Request to get all Products");
        return this.productRepository.findAll().stream().map(mappper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id)
                .map(mappper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("Product not found " + id));
    }

    public ProductResponseDto create(final Product product) {
        log.debug("Request to create Product : {}", product);
        return mappper.toDto(productRepository.save(product));
    }

    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        this.productRepository.deleteById(id);
    }
}