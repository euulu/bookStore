package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.appuser.AppUserDto;
import org.eulu.bookshop.model.AppUser;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface AppUserMapper {
    AppUserDto toDto(AppUser user);
}
