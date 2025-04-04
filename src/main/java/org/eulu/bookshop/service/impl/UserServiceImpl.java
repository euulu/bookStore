package org.eulu.bookshop.service.impl;

import jakarta.transaction.Transactional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.user.CreateUserRequestDto;
import org.eulu.bookshop.dto.user.UserDto;
import org.eulu.bookshop.exception.RegistrationException;
import org.eulu.bookshop.mapper.UserMapper;
import org.eulu.bookshop.model.Role;
import org.eulu.bookshop.model.User;
import org.eulu.bookshop.repository.RoleRepository;
import org.eulu.bookshop.repository.UserRepository;
import org.eulu.bookshop.service.ShoppingCartService;
import org.eulu.bookshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto register(CreateUserRequestDto createUserRequestDto) {
        if (userRepository.existsUserByEmailEqualsIgnoreCase(createUserRequestDto.email())) {
            throw new RegistrationException("User with email: "
                    + createUserRequestDto.email() + " already exists");
        }
        User user = userMapper.toEntity(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(createUserRequestDto.password()));
        Role roleUser = roleRepository.findByName(Role.RoleName.ROLE_USER);
        user.setRoles(Set.of(roleUser));
        shoppingCartService.createShoppingCart(user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}
