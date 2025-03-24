package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.dto.appuser.CreateUserRequestDto;

public interface AppUserService {
    UserDto register(CreateUserRequestDto createUserRequestDto);
}
