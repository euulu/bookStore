package org.eulu.bookshop.dto.cartitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateCartItemRequestDto(
        @NotNull
        @Positive
        Integer quantity
) {
}
