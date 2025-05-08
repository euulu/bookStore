package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.dto.order.UpdateOrderRequestDto;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Place a new order",
            description = "Place a new order"
    )
    public OrderDto createOrder(
            Authentication authentication,
            @RequestBody @Valid CreateOrderRequestDto orderRequestDto
    ) {
        User currentUser = (User) authentication.getPrincipal();
        return orderService.save(currentUser.getId(), orderRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get the order",
            description = "Returns current user order"
    )
    public List<OrderDto> getOrder(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return orderService.get(currentUser.getId());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Update order status",
            description = "Update order status by order id"
    )
    public OrderDto updateOrder(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOrderRequestDto orderRequestDto
    ) {
        return orderService.update(id, orderRequestDto);
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get order items",
            description = "Get all order items from order by order id"
    )
    public List<OrderItemDto> getOrderItems(
            @PathVariable(name = "id") Long orderId
    ) {
        return orderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(
            summary = "Get single order item",
            description = "Get order item by id and order id"
    )
    public OrderItemDto getOrderItem(
            @PathVariable(name = "orderId") Long orderId,
            @PathVariable(name = "itemId") Long orderItemId
    ) {
        return orderService.getOrderItemByIdAndOrderId(orderItemId, orderId);
    }
}
