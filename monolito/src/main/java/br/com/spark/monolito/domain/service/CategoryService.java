package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.CategoryDto;
import br.com.spark.monolito.domain.model.Category;
import br.com.spark.monolito.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryDto> findAll() {
        log.debug("Request to get all Categories");
        return this.repository
                .findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(final Long id) {
        log.debug("Request to get Category: {}", id);
        return this.repository.findById(id)
                .map(CategoryService::mapToDto)
                .orElseThrow(IllegalArgumentException::new);
    }

    public CategoryDto create(final CategoryDto categoryDto) {
        log.debug("Request to create Category: {}", categoryDto);
        return mapToDto(this.repository.save(new Category(categoryDto
                .getName(), categoryDto.getDescription(), Collections.emptySet())));
    }

    public void delete(final Long id) {
        log.debug("Request to delete Category: {}", id);
        this.repository.deleteById(id);
    }

    public static CategoryDto mapToDto(final Category category) {
        return ofNullable(category)
                .map(dto -> new CategoryDto(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        category.getProducts().stream().map(ProductService::mapToDto)
                        .collect(Collectors.toSet())
                )).orElse(null);
    }
}
