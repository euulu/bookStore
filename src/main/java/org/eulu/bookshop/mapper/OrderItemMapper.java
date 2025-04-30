package org.eulu.bookshop.mapper;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.orderitem.OrderItemDto;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.Order;
import org.eulu.bookshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "order", target = "order")
    @Mapping(source = "cartItem.book", target = "book")
    @Mapping(source = "cartItem.quantity", target = "quantity")
    @Mapping(source = "cartItem", target = "price", qualifiedByName = "calculateItemPrice")
    OrderItem cartItemToOrderItem(CartItem cartItem, Order order);

    default Set<OrderItem> cartItemsToOrderItems(Set<CartItem> cartItems, Order order) {
        return cartItems.stream()
                .map(cartItem -> cartItemToOrderItem(cartItem, order))
                .collect(Collectors.toSet());
    }

    @Named("calculateItemPrice")
    default BigDecimal calculateItemPrice(CartItem cartItem) {
        return cartItem.getBook().getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
