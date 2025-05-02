package org.eulu.bookshop.repository;

import java.util.List;
import java.util.Optional;
import org.eulu.bookshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder_Id(Long orderId);

    Optional<OrderItem> findByIdAndOrder_Id(Long orderItemId, Long orderId);
}
