package org.eulu.bookshop.repository.category;

import org.eulu.bookshop.model.Category;
import org.springframework.data.jpa.domain.Specification;

public class NameSpecification {
    private static final String NAME_COL_NAME = "name";

    public static Specification<Category> containsName(String providedName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get(NAME_COL_NAME)),
                "%" + providedName.toLowerCase() + "%"
        );
    }
}
