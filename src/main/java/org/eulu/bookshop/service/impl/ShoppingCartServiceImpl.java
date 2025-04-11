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
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDto saveCartItem(
            Long userId,
            CreateCartItemRequestDto createCartItemRequestDto
    ) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + userId));
        Book book = bookRepository.findById(createCartItemRequestDto.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find the book with id:"
                        + userId));
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
    public ShoppingCartDto findShoppingCart(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartDto updateCartItem(
            Long userId,
            Long cartItemId,
            UpdateCartItemRequestDto cartItemRequestDto
    ) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("No shopping cart found "
                        + "for the user with id:" + userId));
        CartItem cartItem = cartItemRepository
                .findByIdAndShoppingCart_Id(cartItemId, shoppingCart.getId())
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
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        shoppingCartRepository.save(cart);
    }
}
