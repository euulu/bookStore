package org.eulu.bookshop.repository;

import org.eulu.bookshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>,
        JpaSpecificationExecutor<Category> {
    boolean existsByName(String name);

    boolean existsCategoryById(Long id);
}
