package org.eulu.bookshop.mapper;

import java.math.BigDecimal;
import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.model.CartItem;
import org.eulu.bookshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(source = "cartItem", target = "price", qualifiedByName = "calculateItemPrice")
    OrderItem toOrderItem(CartItem cartItem);

    @Named("calculateItemPrice")
    default BigDecimal calculateItemPrice(CartItem cartItem) {
        return cartItem.getBook().getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}
