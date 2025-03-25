package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.mapper.UserMapper;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.existsUserByEmailEqualsIgnoreCase(createUserRequestDto.email())) {
            throw new RegistrationException("User with email: "
                    + createUserRequestDto.email() + " already exists");
        }
        User user = userMapper.toEntity(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(createUserRequestDto.password()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
