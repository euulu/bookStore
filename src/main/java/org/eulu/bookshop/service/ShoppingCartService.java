package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.cartitem.UpdateCartItemRequestDto;
import org.eulu.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.eulu.bookshop.model.User;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCartDto saveCartItem(
            Authentication authentication,
            CreateCartItemRequestDto createCartItemRequestDto
    );

    ShoppingCartDto findShoppingCart(Authentication authentication);

    ShoppingCartDto updateCartItem(
            Long userId,
            Long cartItemId,
            UpdateCartItemRequestDto cartItemRequestDto
    );

    void deleteCartItem(Long userId, Long cartItemId);

    void createShoppingCart(User user);
}
