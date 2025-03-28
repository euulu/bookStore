package org.eulu.bookshop.mapper;

import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = Mapper.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
