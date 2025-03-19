package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    Page<BookDto> findAll(Pageable pageable);

    Page<BookDto> findAll(BookSearchParametersDto searchParameters, Pageable pageable);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);
}
