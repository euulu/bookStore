package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.appuser.UserDto;
import org.eulu.bookshop.dto.appuser.CreateUserRequestDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.mapper.AppUserMapper;
import org.eulu.bookshop.model.AppUser;
import org.eulu.bookshop.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;
    private final AppUserMapper userMapper;

    @Override
    public UserDto register(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.findAppUsersByEmail(createUserRequestDto.email()).isPresent()) {
            throw new RegistrationException("Cannot register user");
        }
        AppUser user = new AppUser();
        user.setEmail(createUserRequestDto.email());
        user.setPassword(createUserRequestDto.password());
        user.setFirstName(createUserRequestDto.firstName());
        user.setLastName(createUserRequestDto.lastName());
        user.setShippingAddress(createUserRequestDto.shippingAddress());
        AppUser savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
