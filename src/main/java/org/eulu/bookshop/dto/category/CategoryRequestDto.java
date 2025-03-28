package org.eulu.bookshop.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank
        String name,
        String description
) {
}
