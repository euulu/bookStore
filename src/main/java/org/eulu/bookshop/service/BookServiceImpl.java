package org.eulu.bookshop.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.BookMapper;
import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.repository.BookRepository;
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
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book updatedBook = bookMapper.toModel(requestDto);
        updatedBook.setId(id);
        Book savedBook = bookRepository.save(updatedBook);
        return bookMapper.toDto(savedBook);
    }
}
