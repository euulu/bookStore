package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.model.ShoppingCart;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {
    ShoppingCart saveCartItem(
            Authentication authentication,
            CreateCartItemRequestDto createCartItemRequestDto
    );
}
