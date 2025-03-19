package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;
import org.eulu.bookshop.service.BookService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Operations related to book management")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new book",
            description = "Create a new book"
    )
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get book by id",
            description = "Retrieves detailed information about a specific book"
    )
    public BookDto getBookById(
            @Parameter(description = "Id of the book to retrieve", example = "42")
            @PathVariable Long id
    ) {
        return bookService.findById(id);
    }

    @GetMapping
    @Operation(
            summary = "Get all books",
            description = "Retrieves a paginated list of all available books"
    )
    public List<BookDto> getAll(@ParameterObject Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search books",
            description = "Search books using various filters and parameters with pagination"
    )
    public List<BookDto> searchBooks(
            @ParameterObject BookSearchParametersDto searchParameters,
            @ParameterObject Pageable pageable
    ) {
        return bookService.findAll(searchParameters, pageable);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update book",
            description = "Update existing book with new data"
    )
    public BookDto updateBook(
            @PathVariable Long id,
            @RequestBody @Valid CreateBookRequestDto requestDto
    ) {
        return bookService.update(id, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a book by ID",
            description = "Delete a book from the system"
    )
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
