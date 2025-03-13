package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class TitleSpecification {
    public static Specification<Book> containsTitle(String providedTitle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + providedTitle.toLowerCase() + "%"
        );
    }
}
