package org.eulu.bookshop.repository;

import org.eulu.bookshop.dto.category.CategorySearchParametersDto;
import org.eulu.bookshop.model.Category;
import org.eulu.bookshop.repository.category.NameSpecification;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> getSpecification(CategorySearchParametersDto searchParameters) {
        Specification<Category> spec = Specification.where(null);
        if (searchParameters.name() != null && !searchParameters.name().trim().isEmpty()) {
            spec = spec.and(NameSpecification.containsName(searchParameters.name()));
        }
        return spec;
    }
}
