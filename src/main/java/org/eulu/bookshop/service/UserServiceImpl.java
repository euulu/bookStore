package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.mapper.UserMapper;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto register(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.existsUserByEmailEqualsIgnoreCase(createUserRequestDto.email())) {
            throw new RegistrationException("Cannot register user "
                    + "with email" + createUserRequestDto.email());
        }
        User user = userMapper.toEntity(createUserRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
