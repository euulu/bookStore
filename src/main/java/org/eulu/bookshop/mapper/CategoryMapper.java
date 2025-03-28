package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.eulu.bookshop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);

    void updateCategoryFromDto(
            CategoryRequestDto categoryRequestDto,
            @MappingTarget Category category
    );
}
