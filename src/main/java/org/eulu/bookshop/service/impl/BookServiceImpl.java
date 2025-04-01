package org.eulu.bookshop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.book.BookDto;
import org.eulu.bookshop.dto.book.BookSearchParametersDto;
import org.eulu.bookshop.dto.book.CreateBookRequestDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.BookMapper;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.model.Category;
import org.eulu.bookshop.repository.BookRepository;
import org.eulu.bookshop.repository.BookSpecification;
import org.eulu.bookshop.repository.CategoryRepository;
import org.eulu.bookshop.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        Book book = bookMapper.toModel(bookRequestDto);
        Set<Category> bookCategories = getBookCategories(bookRequestDto.getCategoriesId());
        book.setCategories(bookCategories);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find the book with id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public Page<BookDto> findAll(BookSearchParametersDto searchParameters, Pageable pageable) {
        Specification<Book> spec = BookSpecification.getSpecification(searchParameters);
        return bookRepository.findAll(spec, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Cannot find the book with id: " + id));
        bookMapper.updateBookFromDto(requestDto, existingBook);
        bookRepository.save(existingBook);
        return bookMapper.toDto(existingBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private Set<Category> getBookCategories(Set<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) {
            return new HashSet<>();
        }
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
        if (categories.size() != categoryIds.size()) {
            List<Long> foundIds = categories.stream()
                    .map(Category::getId)
                    .toList();
            List<Long> notFoundIds = categoryIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new EntityNotFoundException("Categories not found with ids: " + notFoundIds);
        }
        return categories;
    }
}
