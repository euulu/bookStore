package org.eulu.bookshop.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.book.BookDto;
import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.eulu.bookshop.dto.category.CategorySearchParametersDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.BookMapper;
import org.eulu.bookshop.mapper.CategoryMapper;
import org.eulu.bookshop.model.Category;
import org.eulu.bookshop.repository.BookRepository;
import org.eulu.bookshop.repository.CategoryRepository;
import org.eulu.bookshop.repository.CategorySpecification;
import org.eulu.bookshop.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

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
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find category with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto save(CategoryRequestDto category) {
        if (categoryRepository.existsByName(category.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "The category with this name already exists. Name: " + category.name()
            );
        }
        Category savedCategory = categoryRepository.save(categoryMapper.toEntity(category));
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto update(Long id, CategoryRequestDto category) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find category to update, id: " + id));
        categoryMapper.updateCategoryFromDto(category, categoryToUpdate);
        categoryRepository.save(categoryToUpdate);
        return categoryMapper.toDto(categoryToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<BookDto> getBooksByCategoryId(Long id, Pageable pageable) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find category with id: " + id));
        return bookRepository.findBooksByCategoriesContaining(Set.of(category), pageable)
                .map(bookMapper::toDto);
    }
}
