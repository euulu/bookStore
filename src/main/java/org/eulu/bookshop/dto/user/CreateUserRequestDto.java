package org.eulu.bookshop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eulu.bookshop.annotation.FieldMatch;
import org.hibernate.validator.constraints.Length;

@FieldMatch(password = "password", repeatPassword = "repeatPassword")
public record CreateUserRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 6, max = 20)
        String password,
        @NotBlank
        @Length(min = 6, max = 20)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress
) {
}
