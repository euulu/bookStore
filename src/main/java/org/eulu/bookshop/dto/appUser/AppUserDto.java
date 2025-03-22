package org.eulu.bookshop.dto.appUser;

public record AppUserDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
