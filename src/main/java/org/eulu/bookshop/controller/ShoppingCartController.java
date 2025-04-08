package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.cartitem.CreateCartItemRequestDto;
import org.eulu.bookshop.dto.cartitem.UpdateCartItemRequestDto;
import org.eulu.bookshop.dto.shoppingcart.ShoppingCartDto;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Shopping cart", description = "Operations related to shopping cart management")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Add cart item to the shopping cart",
            description = "Add book id and books quantity to the "
                    + "currently logged in users shopping cart"
    )
    public ShoppingCartDto addBookToShoppingCart(
            Authentication authentication,
            @RequestBody @Valid CreateCartItemRequestDto cartItemRequest
    ) {
        return shoppingCartService.saveCartItem(authentication, cartItemRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Get shopping cart",
            description = "Get current logged in user shopping cart"
    )
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.findShoppingCart(authentication);
    }

    @PutMapping("/items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Update cart item",
            description = "Update book quantity in the cart item "
                    + "by cart item ID"
    )
    public ShoppingCartDto updateCartItem(
            Authentication authentication,
            @Parameter(description = "Cart item id to update", example = "42")
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateCartItemRequestDto cartItemRequestDto
    ) {
        return shoppingCartService
                .updateCartItem(authentication, cartItemId, cartItemRequestDto);
    }

    @DeleteMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Delete a cart item by ID",
            description = "Delete a cart item from the system"
    )
    public void deleteCartItem(
            Authentication authentication,
            @Parameter(description = "Cart item id to delete", example = "42")
            @PathVariable Long cartItemId
    ) {
        User currentUser = (User) authentication.getPrincipal();
        shoppingCartService.deleteCartItem(currentUser.getId(), cartItemId);
    }
}
