package org.eulu.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookDto {
    @Schema(
            description = "Unique identifier of the book",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;
    @Schema(
            description = "Title of the book",
            example = "The Great Adventure",
            maxLength = 255
    )
    private String title;
    @Schema(
            description = "Full name of the author",
            example = "J.K. Rowling",
            maxLength = 255
    )
    private String author;
    @Schema(
            description = "13-digit ISBN number",
            example = "978-3-16-148410-0"
    )
    private String isbn;
    @Schema(
            description = "Price of the book",
            example = "29.99",
            minimum = "0.00",
            implementation = BigDecimal.class
    )
    private BigDecimal price;
    @Schema(
            description = "Brief summary of the book content",
            example = "A thrilling journey through magical realms...",
            maxLength = 500
    )
    private String description;
    @Schema(
            description = "URL to book cover image",
            example = "https://example.com/covers/cover-image.jpg",
            format = "uri"
    )
    private String coverImage;
}
