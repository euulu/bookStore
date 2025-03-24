package org.eulu.bookshop.mapper;

import org.eulu.bookshop.config.MapperConfig;
import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;
import org.eulu.bookshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(CreateUserRequestDto userRequestDto);
}
