package org.eulu.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.appuser.AppUserDto;
import org.eulu.bookshop.dto.appuser.CreateAppUserRequestDto;
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
    public AppUserDto register(CreateAppUserRequestDto createAppUserRequestDto) {
        if (userRepository.findAppUsersByEmail(createAppUserRequestDto.email()).isPresent()) {
            throw new RegistrationException("Cannot register user");
        }
        AppUser user = new AppUser();
        user.setEmail(createAppUserRequestDto.email());
        user.setPassword(createAppUserRequestDto.password());
        user.setFirstName(createAppUserRequestDto.firstName());
        user.setLastName(createAppUserRequestDto.lastName());
        user.setShippingAddress(createAppUserRequestDto.shippingAddress());
        AppUser savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
