package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class IsbnSpecification {
    public static Specification<Book> hasIsbn(String providedIsbn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("isbn"),
                providedIsbn
        );
    }
}
