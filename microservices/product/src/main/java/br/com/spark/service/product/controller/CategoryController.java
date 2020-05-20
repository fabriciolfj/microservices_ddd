package br.com.spark.service.product.controller;

import br.com.spark.service.product.domain.dto.CategoryDto;
import br.com.spark.service.product.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.spark.service.product.web.Web.API;


@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable final Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(@RequestBody final CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        this.categoryService.delete(id);
    }
}
