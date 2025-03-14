package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    private static final String AUTHOR_COL_NAME = "author";

    public static Specification<Book> containsAuthor(String providedAuthor) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(AUTHOR_COL_NAME)),
                "%" + providedAuthor.toLowerCase() + "%"
        );
    }
}
