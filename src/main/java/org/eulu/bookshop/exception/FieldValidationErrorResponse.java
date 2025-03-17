package org.eulu.bookshop.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public record FieldValidationErrorResponse(
        LocalDateTime timestamp,
        HttpStatus status,
        List<String> errors
) {
}
