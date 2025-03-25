package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Register new user",
            description = "Register new user"
    )
    public UserDto register(@RequestBody CreateUserRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }
}
