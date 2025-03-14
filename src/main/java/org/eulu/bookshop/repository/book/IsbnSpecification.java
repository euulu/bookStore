package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class IsbnSpecification {
    private static final String ISBN_COL_NAME = "isbn";

    public static Specification<Book> hasIsbn(String providedIsbn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ISBN_COL_NAME),
                providedIsbn
        );
    }
}
