package org.eulu.bookshop.dto.order;

import jakarta.validation.constraints.NotNull;
import org.eulu.bookshop.model.Status;

public record UpdateOrderRequestDto(
        @NotNull
        Status status
) {
}
