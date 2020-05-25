package br.com.spark.service.product.api.controller;

import br.com.spark.service.product.api.dto.request.ProductRequestDto;
import br.com.spark.service.product.api.dto.request.ProductRequestMapper;
import br.com.spark.service.product.api.dto.response.ProductResponseDto;
import br.com.spark.service.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRequestMapper mapper;

    @GetMapping
    public List<ProductResponseDto> findAll() {
        log.info("find all products");
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto dto) {
        log.info("created product: " + dto.toString());
        return this.productService.create(mapper.toDomain(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }
}