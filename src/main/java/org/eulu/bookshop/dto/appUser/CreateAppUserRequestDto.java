package org.eulu.bookshop.dto.appUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eulu.bookshop.annotation.FieldMatch;

@FieldMatch(password = "password", repeatPassword = "repeatPassword")
public record CreateAppUserRequestDto(
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
