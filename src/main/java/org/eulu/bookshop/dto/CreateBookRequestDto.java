package org.eulu.bookshop.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Length(max = 255)
    private String title;
    @NotNull
    @Length(max = 255)
    private String author;
    @NotNull
    private String isbn;
    @NotNull
    @Min(0)
    @Max(999999999)
    private BigDecimal price;
    @Length(max = 500)
    private String description;
    private String coverImage;
}
