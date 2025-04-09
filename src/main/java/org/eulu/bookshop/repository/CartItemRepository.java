package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsCartItemById(Long id);

    Optional<CartItem> findByIdAndShoppingCart_Id(Long id, Long shoppingCartId);
}
