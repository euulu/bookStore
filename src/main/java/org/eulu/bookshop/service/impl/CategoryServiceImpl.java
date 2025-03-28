package org.eulu.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategorySearchParametersDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.CategoryMapper;
import org.eulu.bookshop.model.Category;
import org.eulu.bookshop.repository.CategoryRepository;
import org.eulu.bookshop.repository.CategorySpecification;
import org.eulu.bookshop.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toDto);
    }

    @Override
    public Page<CategoryDto> findAll(
            CategorySearchParametersDto searchParameters,
            Pageable pageable
    ) {
        Specification<Category> spec = CategorySpecification.getSpecification(searchParameters);
        return categoryRepository.findAll(spec, pageable)
                .map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find category with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CategoryRequestDto category) {
        Category savedCategory = categoryRepository.save(categoryMapper.toEntity(category));
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto update(Long id, CategoryRequestDto category) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find category to update, id: " + id));
        categoryMapper.updateCategoryFromDto(category, categoryToUpdate);
        Category savedCategory = categoryRepository.save(categoryToUpdate);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
