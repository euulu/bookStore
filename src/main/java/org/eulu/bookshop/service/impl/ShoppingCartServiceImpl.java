package org.eulu.bookshop.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.cartitem.UpdateCartItemRequestDto;
import org.eulu.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.CartItemMapper;
import org.eulu.bookshop.mapper.ShoppingCartMapper;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.ShoppingCart;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.BookRepository;
import org.eulu.bookshop.repository.CartItemRepository;
import org.eulu.bookshop.repository.ShoppingCartRepository;
import org.eulu.bookshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartDto saveCartItem(
            Authentication authentication,
            CreateCartItemRequestDto createCartItemRequestDto
    ) {
        User currentUser = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + currentUser.getId()));
        Book book = bookRepository.findById(createCartItemRequestDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find the book with id:"
                        + currentUser.getId()));
        shoppingCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getBook().equals(book))
                .findFirst()
                .ifPresentOrElse(
                    cartItem -> cartItem.setQuantity(createCartItemRequestDto.quantity()),
                    () -> {
                        CartItem cartItem = cartItemMapper.toEntity(createCartItemRequestDto);
                        cartItem.setBook(book);
                        cartItem.setShoppingCart(shoppingCart);
                        shoppingCart.getCartItems().add(cartItem);
                    });
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto findShoppingCart(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + currentUser.getId()));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateCartItem(
            Authentication authentication,
            Long cartItemId,
            UpdateCartItemRequestDto cartItemRequestDto
    ) {
        User currentUser = (User) authentication.getPrincipal();
        validateCartItemOwnership(cartItemId, currentUser.getId());
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + currentUser.getId()));
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCart(cartItemId, shoppingCart)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find cart item "
                        + "with id: " + cartItemId));
        cartItemMapper.updateCartItemFromDto(cartItemRequestDto, cartItem);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItem(Long currentUserId, Long cartItemId) {
        if (!cartItemRepository.existsCartItemById(cartItemId)) {
            throw new EntityNotFoundException("Cannot find cart item with id: " + cartItemId);
        }
        validateCartItemOwnership(cartItemId, currentUserId);
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        shoppingCartRepository.save(cart);
    }

    public void validateCartItemOwnership(Long cartItemId, Long userId) {
        if (!cartItemRepository.existsByIdAndShoppingCart_User_Id(cartItemId, userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete others cart items"
            );
        }
    }
}
