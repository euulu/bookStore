package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.category.CategoryDto;
import org.eulu.bookshop.dto.category.CategoryRequestDto;
import org.eulu.bookshop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequestDto categoryRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateCategoryFromDto(
            CategoryRequestDto categoryRequestDto,
            @MappingTarget Category category
    );
}
