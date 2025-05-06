package org.eulu.bookshop.repository;

import java.util.List;
import java.util.Optional;
import org.eulu.bookshop.model.Order;
import org.eulu.bookshop.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findByUser(User user);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findById(Long id);
}
