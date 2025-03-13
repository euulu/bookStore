package org.eulu.bookshop.repository;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
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
            Predicate predicate = criteriaBuilder.conjunction();
            if (providedMinPrice != null && providedMaxPrice != null) {
                predicate = criteriaBuilder.between(price, providedMinPrice, providedMaxPrice);
            } else if (providedMinPrice != null) {
                predicate = criteriaBuilder.greaterThanOrEqualTo(price, providedMinPrice);
            } else if (providedMaxPrice != null) {
                predicate = criteriaBuilder.lessThanOrEqualTo(price, providedMaxPrice);
            }
            return predicate;
        };
    }
}
