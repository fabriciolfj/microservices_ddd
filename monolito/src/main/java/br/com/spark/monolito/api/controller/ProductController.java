package br.com.spark.monolito.api.controller;

import br.com.spark.monolito.domain.dto.ProductDto;
import br.com.spark.monolito.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.spark.monolito.infrastructure.web.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }
}