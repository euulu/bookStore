package org.eulu.bookshop.repository;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
