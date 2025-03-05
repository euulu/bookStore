package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
