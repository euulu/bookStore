package org.eulu.bookshop.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;
import org.eulu.bookshop.model.Status;

public record OrderDto(
        Long id,
        Long userId,
        Status status,
        BigDecimal total,
        LocalDateTime orderDate,
        String shippingAddress,
        Set<OrderItemDto> orderItems
) {
}
