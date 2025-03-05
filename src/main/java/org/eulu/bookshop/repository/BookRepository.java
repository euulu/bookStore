package org.eulu.bookshop.repository;

import java.util.List;
import org.eulu.bookshop.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
