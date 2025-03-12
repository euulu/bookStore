package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    List<BookDto> findAll();

    List<BookDto> findAll(BookSearchParametersDto searchParameters);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);
}
