package org.eulu.bookshop.dto.shoppingcart;

import java.util.Set;
import org.eulu.bookshop.dto.cartitem.CartItemDto;

public record ShoppingCartDto(
        Long id,
        Long userId,
        Set<CartItemDto> cartItems
) {
}
