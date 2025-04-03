package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.ShoppingCart;
import org.eulu.bookshop.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = {"cartItems.book"})
    Optional<ShoppingCart> findShoppingCartByUser(User user);

    @Override
    @EntityGraph(attributePaths = "cartItems.book")
    Optional<ShoppingCart> findById(Long id);
}
