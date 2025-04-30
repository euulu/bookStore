package org.eulu.bookshop.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.order.CreateOrderRequestDto;
import org.eulu.bookshop.dto.order.OrderDto;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.Order;
import org.eulu.bookshop.model.OrderItem;
import org.eulu.bookshop.model.Status;
import org.eulu.bookshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "cartItems", target = "total", qualifiedByName = "calculateTotal")
    @Mapping(target = "orderDate", expression = "java(getLocalDateTimeNow())")
    @Mapping(source = "cartItems", target = "orderItems", qualifiedByName = "getOrderItems")
    @Mapping(source = "orderRequestDto.shippingAddress", target = "shippingAddress")
    Order toEntity(
            CreateOrderRequestDto orderRequestDto,
            User user,
            Status status,
            Set<CartItem> cartItems
    );

    @Named("calculateTotal")
    default BigDecimal calculateTotal(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(item -> item.getBook().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Named("getLocalDateTimeNow")
    default LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now();
    }

    @Named("getOrderItems")
    default Set<OrderItem> getOrderItems(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::cartItemToOrderItem)
                .collect(Collectors.toSet());
    }

    @Named("cartItemToOrderItem")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem cartItemToOrderItem(CartItem cartItem);
}
