package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsCartItemById(Long id);

    boolean existsByIdAndShoppingCart_User_Id(Long cartItemId, Long userId);

    Optional<CartItem> findByIdAndShoppingCart(Long id, ShoppingCart shoppingCart);
}
