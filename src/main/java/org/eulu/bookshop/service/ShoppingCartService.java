package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartDto saveCartItem(
            Authentication authentication,
            CreateCartItemRequestDto createCartItemRequestDto
    );

    ShoppingCartDto findShoppingCart(Authentication authentication);
}
