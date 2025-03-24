package org.eulu.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.appuser.AppUserDto;
import org.eulu.bookshop.dto.appuser.CreateAppUserRequestDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.service.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AppUserService userService;

    @PostMapping("/registration")
    public AppUserDto register(CreateAppUserRequestDto request) throws RegistrationException {
        return userService.register(request);
    }
}
