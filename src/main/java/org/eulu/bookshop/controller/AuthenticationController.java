package org.eulu.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.dto.appuser.CreateAppUserRequestDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.service.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthenticationController {
    private final AppUserService userService;

    @PostMapping("/registration")
    @Operation(
            summary = "Register new user",
            description = "Register new user"
    )
    public UserDto register(CreateAppUserRequestDto request) throws RegistrationException {
        return userService.register(request);
    }
}
