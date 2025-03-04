package org.eulu.bookshop.repository;

import org.eulu.bookshop.model.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
