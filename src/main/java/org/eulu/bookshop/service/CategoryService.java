package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategorySearchParametersDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> findAll(Pageable pageable);

    Page<CategoryDto> findAll(CategorySearchParametersDto searchParameters, Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto save(CategoryRequestDto category);

    CategoryDto update(Long id, CategoryRequestDto category);

    void deleteById(Long id);
}
