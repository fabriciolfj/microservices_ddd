package br.com.spark.monolito.api.controller;

import br.com.spark.monolito.domain.dto.CategoryDto;
import br.com.spark.monolito.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.spark.monolito.infrastructure.web.Web.API;

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
    public CategoryDto create(final CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        this.categoryService.delete(id);
    }
}
