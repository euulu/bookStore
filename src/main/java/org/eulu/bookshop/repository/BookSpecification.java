package org.eulu.bookshop.repository;

import org.eulu.bookshop.dto.BookSearchParametersDto;
import org.eulu.bookshop.model.Book;
import org.eulu.bookshop.repository.book.AuthorSpecification;
import org.eulu.bookshop.repository.book.IsbnSpecification;
import org.eulu.bookshop.repository.book.PriceSpecification;
import org.eulu.bookshop.repository.book.TitleSpecification;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> getSpecification(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.title() != null && !searchParameters.title().trim().isEmpty()) {
            spec = spec.and(TitleSpecification.containsTitle(searchParameters.title()));
        }
        if (searchParameters.author() != null && !searchParameters.author().trim().isEmpty()) {
            spec = spec.and(AuthorSpecification.containsAuthor(searchParameters.author()));
        }
        if (searchParameters.isbn() != null && !searchParameters.isbn().trim().isEmpty()) {
            spec = spec.and(IsbnSpecification.hasIsbn(searchParameters.isbn()));
        }
        if (searchParameters.minPrice() != null || searchParameters.maxPrice() != null) {
            spec = spec.and(PriceSpecification.isPriceInRange(
                            searchParameters.minPrice(),
                            searchParameters.maxPrice()
                    )
            );
        }
        return spec;
    }
}
