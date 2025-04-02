package org.eulu.bookshop.repository;

import java.util.Set;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Page<Book> findBooksByCategoriesContaining(Set<Category> categories, Pageable pageable);
}
