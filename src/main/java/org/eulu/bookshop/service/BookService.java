package org.eulu.bookshop.service;

import org.eulu.bookshop.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
