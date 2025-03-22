package org.eulu.bookshop.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotBlank
    @Length(max = 255)
    @Schema(
            description = "Title of the book",
            example = "The Great Adventure",
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;
    @NotBlank
    @Length(max = 255)
    @Schema(
            description = "Author's full name",
            example = "George R.R. Martin",
            maxLength = 255,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String author;
    @NotBlank
    @Schema(
            description = "International Standard Book Number (13 digits)",
            example = "978-0-306-40615-7",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String isbn;
    @NotNull
    @Positive
    @Schema(
            description = "Retail price in USD",
            example = "49.99",
            minimum = "0.00",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private BigDecimal price;
    @Length(max = 500)
    @Schema(
            description = "Detailed book description",
            example = "A profound exploration of human nature...",
            maxLength = 500,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String description;
    @Schema(
            description = "URL to cover image",
            example = "https://example.com/covers/silent-observer.jpg",
            format = "uri",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String coverImage;
}
