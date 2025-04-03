package org.eulu.bookshop.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.CartItemMapper;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.ShoppingCart;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.BookRepository;
import org.eulu.bookshop.repository.CartItemRepository;
import org.eulu.bookshop.repository.ShoppingCartRepository;
import org.eulu.bookshop.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ShoppingCart saveCartItem(
            Authentication authentication,
            CreateCartItemRequestDto createCartItemRequestDto
    ) {
        User currentUser = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + currentUser.getId()));
        if (!bookRepository.existsById(createCartItemRequestDto.bookId())) {
            throw new EntityNotFoundException("Cannot find the book with id:"
                    + currentUser.getId());
        }
        CartItem cartItem = cartItemMapper.toEntity(createCartItemRequestDto);
        cartItem.setShoppingCart(shoppingCart);
        cartItemRepository.save(cartItem);
        entityManager.refresh(shoppingCart);
        return shoppingCartRepository.findShoppingCartByUser(currentUser)
                .orElseThrow(() ->
                        new IllegalStateException("Shopping cart disappeared after update"));
    }
}
