package org.eulu.bookshop.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.dto.order.UpdateOrderRequestDto;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.OrderItemMapper;
import org.eulu.bookshop.mapper.OrderMapper;
import org.eulu.bookshop.model.Order;
import org.eulu.bookshop.model.OrderItem;
import org.eulu.bookshop.model.ShoppingCart;
import org.eulu.bookshop.model.Status;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.OrderRepository;
import org.eulu.bookshop.repository.ShoppingCartRepository;
import org.eulu.bookshop.repository.UserRepository;
import org.eulu.bookshop.service.OrderItemService;
import org.eulu.bookshop.service.OrderService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemService orderItemService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

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
        Set<OrderItem> orderItems = orderItemMapper.cartItemsToOrderItems(
                shoppingCart.getCartItems(),
                order
        );
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> get(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Cannot find user with id: " + userId));
        return orderRepository.findByUser(user).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto update(Long orderId, UpdateOrderRequestDto orderRequestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("Cannot find order with id: " + orderId));
        order.setStatus(orderRequestDto.status());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemDto> getOrderItemsByOrderId(Long orderId) {
        return orderItemService.findByOrderId(orderId);
    }

    @Override
    public OrderItemDto getOrderItemByIdAndOrderId(Long orderItemId, Long orderId) {
        return orderItemService.findOrderItem(orderItemId, orderId);
    }
}
