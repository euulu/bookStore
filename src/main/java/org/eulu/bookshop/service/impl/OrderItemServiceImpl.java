package org.eulu.bookshop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;
import org.eulu.bookshop.exception.EntityNotFoundException;
import org.eulu.bookshop.mapper.OrderItemMapper;
import org.eulu.bookshop.model.OrderItem;
import org.eulu.bookshop.repository.OrderItemRepository;
import org.eulu.bookshop.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemDto> findByOrderId(Long orderId) {
        return orderItemRepository.findByOrder_Id(orderId).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto findOrderItem(Long orderItemId, Long orderId) {
        OrderItem orderItem = orderItemRepository
                .findByIdAndOrder_Id(orderItemId, orderId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order item with id: "
                        + orderItemId));
        return orderItemMapper.toDto(orderItem);
    }
}
