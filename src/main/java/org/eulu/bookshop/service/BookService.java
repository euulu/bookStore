package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.dto.BookDto;
import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    BookDto findById(Long id);

    List<BookDto> findAll(Pageable pageable);

    List<BookDto> findAll(BookSearchParametersDto searchParameters);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);
}
