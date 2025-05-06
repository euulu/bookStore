package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.dto.order.UpdateOrderRequestDto;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;

public interface OrderService {
    OrderDto save(Long userId, CreateOrderRequestDto orderDto);

    List<OrderDto> get(Long userId);

    OrderDto update(Long orderId, UpdateOrderRequestDto orderRequestDto);

    List<OrderItemDto> getOrderItemsByOrderId(Long orderId);

    OrderItemDto getOrderItemByIdAndOrderId(Long orderItemId, Long orderId);
}
