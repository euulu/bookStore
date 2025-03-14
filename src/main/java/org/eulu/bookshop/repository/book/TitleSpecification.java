package org.eulu.bookshop.repository.book;

import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class TitleSpecification {
    private static final String TITLE_COL_NAME = "title";

    public static Specification<Book> containsTitle(String providedTitle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(TITLE_COL_NAME)),
                "%" + providedTitle.toLowerCase() + "%"
        );
    }
}
