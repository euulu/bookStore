package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestBody CreateOrderRequestDto orderRequestDto
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
}
