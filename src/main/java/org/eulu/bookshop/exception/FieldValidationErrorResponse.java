package org.eulu.bookshop.exception;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

public record FieldValidationErrorResponse(
        LocalDateTime timestamp,
        HttpStatus status,
        List<String> errors
) {
}
