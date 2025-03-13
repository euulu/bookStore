package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    public static Specification<Book> containsAuthor(String providedAuthor) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("author")),
                "%" + providedAuthor.toLowerCase() + "%"
        );
    }
}
