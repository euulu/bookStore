package org.eulu.bookshop.service;

import java.util.List;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.dto.order.UpdateOrderRequestDto;

public interface OrderService {
    OrderDto save(Long userId, CreateOrderRequestDto orderDto);

    List<OrderDto> get(Long userId);

    OrderDto update(Long orderId, UpdateOrderRequestDto orderRequestDto);
}
