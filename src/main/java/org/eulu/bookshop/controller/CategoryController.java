package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.book.BookDto;
import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.eulu.bookshop.dto.category.CategorySearchParametersDto;
import org.eulu.bookshop.service.CategoryService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new category",
            description = "Create a new category"
    )
    public CategoryDto createCategory(CategoryRequestDto categoryRequestDto) {
        return categoryService.save(categoryRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get all categories",
            description = "Get a pageable list of all available categories"
    )
    public Page<CategoryDto> getAll(@ParameterObject Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Search categories",
            description = "Search categories by category name, "
                    + "get pageable list of all filterred categories"
    )
    public Page<CategoryDto> search(
            @ParameterObject CategorySearchParametersDto searchParameters,
            @ParameterObject Pageable pageable
    ) {
        return categoryService.findAll(searchParameters, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get category by id",
            description = "Retrieves detailed information about a specific catecory"
    )
    public CategoryDto getCategoryById(
            @Parameter(description = "id of the category to retrieve", example = "2")
            @PathVariable Long id
    ) {
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Update a category",
            description = "Update existing cateogory with new data"
    )
    public CategoryDto updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequestDto requestDto
    ) {
        return categoryService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a book bi ID",
            description = "Delete a book by ID from the system"
    )
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get all books from the category",
            description = "Retrieves all books from the given category by category ID"
    )
    public Page<BookDto> getBooksByCategoryId(@PathVariable Long id, Pageable pageable) {
        return categoryService.getBooksByCategoryId(id, pageable);
    }
}
