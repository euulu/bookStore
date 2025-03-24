package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.appuser.CreateUserRequestDto;
import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.mapper.UserMapper;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto register(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.findAppUsersByEmail(createUserRequestDto.email()).isPresent()) {
            throw new RegistrationException("Cannot register user");
        }
        User user = userMapper.toEntity(createUserRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
