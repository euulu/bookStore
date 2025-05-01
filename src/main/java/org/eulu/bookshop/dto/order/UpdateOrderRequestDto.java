package org.eulu.bookshop.dto.order;

import jakarta.validation.constraints.NotBlank;
import org.eulu.bookshop.model.Status;

public record UpdateOrderRequestDto(
        @NotBlank
        Status status
) {
}
