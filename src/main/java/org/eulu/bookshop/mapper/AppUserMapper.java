package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AppUserMapper {
    UserDto toDto(AppUser user);
}
