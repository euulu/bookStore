package org.eulu.bookshop.dto.appuser;

public record AppUserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
