package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;

public interface OrderService {
    OrderDto save(Long userId, CreateOrderRequestDto orderDto);
}
