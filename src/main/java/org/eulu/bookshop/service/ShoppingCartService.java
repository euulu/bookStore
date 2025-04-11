package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.cartitem.UpdateCartItemRequestDto;
import org.eulu.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.eulu.bookshop.model.User;

public interface ShoppingCartService {
    ShoppingCartDto saveCartItem(
            Long userId,
            CreateCartItemRequestDto createCartItemRequestDto
    );

    ShoppingCartDto findShoppingCart(Long userId);

    ShoppingCartDto updateCartItem(
            Long userId,
            Long cartItemId,
            UpdateCartItemRequestDto cartItemRequestDto
    );

    void deleteCartItem(Long userId, Long cartItemId);

    void createShoppingCart(User user);
}
