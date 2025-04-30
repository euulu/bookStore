package org.eulu.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.OrderMapper;
import org.eulu.bookshop.model.Order;
import org.eulu.bookshop.model.ShoppingCart;
import org.eulu.bookshop.model.Status;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.OrderRepository;
import org.eulu.bookshop.repository.ShoppingCartRepository;
import org.eulu.bookshop.repository.UserRepository;
import org.eulu.bookshop.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto save(Long userId, CreateOrderRequestDto orderDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Cannot find user with id: " + userId));
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find shopping cart "
                        + "for user with id: " + userId));
        Order order = orderMapper.toEntity(
                orderDto,
                user,
                Status.PENDING,
                shoppingCart.getCartItems()
        );
        order.getOrderItems().forEach(item -> item.setOrder(order));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
