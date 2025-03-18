package org.eulu.bookshop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotBlank
    @Length(max = 255)
    private String title;
    @NotBlank
    @Length(max = 255)
    private String author;
    @NotBlank
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    @Length(max = 500)
    private String description;
    private String coverImage;
}
