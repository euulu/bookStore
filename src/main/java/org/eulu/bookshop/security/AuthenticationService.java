package org.eulu.bookshop.security;

import lombok.RequiredArgsConstructor;
import org.eulu.bookshop.dto.user.UserLoginRequestDto;
import org.eulu.bookshop.dto.user.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto userRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.email(),
                        userRequest.password()
                )
        );
        String token = jwtUtil.generateToken(auth.getName());
        return new UserLoginResponseDto(token);
    }
}
