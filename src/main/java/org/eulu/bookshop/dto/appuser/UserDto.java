package org.eulu.bookshop.dto.appuser;

public record UserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
