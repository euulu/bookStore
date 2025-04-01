package org.eulu.bookshop.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategorySearchParametersDto(
        @NotBlank
        String name
) {
}
