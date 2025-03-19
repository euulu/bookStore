package org.eulu.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record BookSearchParametersDto(
        @Schema(description = "Title search string", example = "Adventure")
        String title,
        @Schema(description = "Author name filter", example = "Rowling")
        String author,
        @Schema(description = "ISBN number filter", example = "978-3-16-148410-0")
        String isbn,
        @Schema(description = "Minimum price filter", example = "1.98")
        BigDecimal minPrice,
        @Schema(description = "Maximum price filter", example = "985.65")
        BigDecimal maxPrice
) {
}
