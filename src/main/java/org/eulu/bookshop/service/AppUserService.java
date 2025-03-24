package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.dto.appuser.CreateAppUserRequestDto;

public interface AppUserService {
    UserDto register(CreateAppUserRequestDto createAppUserRequestDto);
}
