package org.eulu.bookshop.repository;

import jakarta.persistence.criteria.Path;
import java.math.BigDecimal;
import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> containsTitle(String providedTitle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + providedTitle.toLowerCase() + "%"
        );
    }

    public static Specification<Book> containsAuthor(String providedAuthor) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("author")),
                "%" + providedAuthor.toLowerCase() + "%"
        );
    }

    public static Specification<Book> hasIsbn(String providedIsbn) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("isbn"),
                providedIsbn
        );
    }

    public static Specification<Book> isPriceInRange(
            BigDecimal providedMinPrice,
            BigDecimal providedMaxPrice
    ) {
        return (root, query, criteriaBuilder) -> {
            Path<BigDecimal> price = root.get("price");
            if (providedMinPrice != null && providedMaxPrice != null) {
                criteriaBuilder.between(price, providedMinPrice, providedMaxPrice);
            } else if (providedMinPrice != null) {
                criteriaBuilder.greaterThanOrEqualTo(price, providedMinPrice);
            } else if (providedMaxPrice != null) {
                criteriaBuilder.lessThanOrEqualTo(price, providedMaxPrice);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
