package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;

public interface UserService {
    UserDto register(CreateUserRequestDto createUserRequestDto);
}
