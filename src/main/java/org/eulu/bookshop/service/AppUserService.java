package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.appuser.CreateUserRequestDto;
import org.eulu.bookshop.dto.appuser.UserDto;

public interface AppUserService {
    UserDto register(CreateUserRequestDto createUserRequestDto);
}
