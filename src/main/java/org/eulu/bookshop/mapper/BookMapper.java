package org.eulu.bookshop.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.book.BookDto;
import org.eulu.bookshop.dto.book.CreateBookRequestDto;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(
            target = "categoryIds",
            source = "categories",
            qualifiedByName = "categoriesToIds"
    )
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "categories", ignore = true)
    void updateBookFromDto(CreateBookRequestDto requestDto, @MappingTarget Book book);

    @Named("categoriesToIds")
    default Set<Long> categoriesToIds(Set<Category> categories) {
        return categories == null
                ? Set.of()
                : categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
    }
}
