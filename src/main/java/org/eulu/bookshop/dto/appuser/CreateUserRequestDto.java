package org.eulu.bookshop.dto.appuser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eulu.bookshop.annotation.FieldMatch;

@FieldMatch(password = "password", repeatPassword = "repeatPassword")
public record CreateUserRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress
) {
}
