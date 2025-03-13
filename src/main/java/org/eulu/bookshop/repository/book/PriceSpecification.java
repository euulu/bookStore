package org.eulu.bookshop.repository.book;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import org.eulu.bookshop.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class PriceSpecification {
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
