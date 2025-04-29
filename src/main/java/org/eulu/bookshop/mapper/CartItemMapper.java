package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.cartitem.CartItemDto;
import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.cartitem.UpdateCartItemRequestDto;
import org.eulu.bookshop.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(source = "bookId", target = "book", qualifiedByName = "bookById")
    CartItem toEntity(CreateCartItemRequestDto cartItemRequestDto);

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shoppingCart", ignore = true)
    @Mapping(target = "book", ignore = true)
    void updateCartItemFromDto(
            UpdateCartItemRequestDto cartItemRequestDto,
            @MappingTarget CartItem cartItem
    );
}
