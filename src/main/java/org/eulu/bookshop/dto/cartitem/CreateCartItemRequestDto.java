package org.eulu.bookshop.dto.cartitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        @NotNull
        @Positive
        Long bookId,
        @NotNull
        @Positive
        int quantity
) {
}
