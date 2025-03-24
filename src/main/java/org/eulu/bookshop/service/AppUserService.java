package org.eulu.bookshop.service;

import org.eulu.bookshop.dto.appuser.AppUserDto;
import org.eulu.bookshop.dto.appuser.CreateAppUserRequestDto;

public interface AppUserService {
    AppUserDto register(CreateAppUserRequestDto createAppUserRequestDto);
}
