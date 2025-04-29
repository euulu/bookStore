package org.eulu.bookshop.repository;

import java.util.Optional;
import java.util.Set;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Page<Book> findBooksByCategoriesContaining(Set<Category> categories, Pageable pageable);

    boolean existsBookById(Long id);

    @EntityGraph(attributePaths = {"categories"})
    @NonNull Page<Book> findAll(@NonNull Pageable pageable);

    @EntityGraph(attributePaths = {"categories"})
    @NonNull Optional<Book> findById(@NonNull Long id);
}
