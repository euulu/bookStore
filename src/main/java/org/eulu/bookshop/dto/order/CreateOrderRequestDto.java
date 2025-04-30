package org.eulu.bookshop.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateOrderRequestDto(
        @NotBlank
        @Length(max = 500)
        @Schema(
                description = "Order address",
                example = "Kharkivske Shose 201/203 Darnytskyi Raion 02121 Kyiv",
                maxLength = 500,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String shippingAddress
) {
}
