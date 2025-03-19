package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.BookMapper;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.repository.BookRepository;
import org.eulu.bookshop.repository.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto book) {
        Book savedBook = bookRepository.save(bookMapper.toModel(book));
        return bookMapper.toDto(savedBook);
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
        Book savedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
