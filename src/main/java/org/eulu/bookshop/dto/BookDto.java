package org.eulu.bookshop.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
