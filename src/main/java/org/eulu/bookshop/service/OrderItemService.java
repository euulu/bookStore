package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;

public interface OrderItemService {
    List<OrderItemDto> findByOrderId(Long orderId);

    OrderItemDto findOrderItem(
            Long orderItemId,
            Long orderId
    );
}
