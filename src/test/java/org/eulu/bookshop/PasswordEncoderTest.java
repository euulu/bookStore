package org.eulu.bookshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Explicitly use BCrypt

    @Test
    void encodePassword() {
        String rawPassword = "qwerty";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Encoded Password: " + encodedPassword);

        // Verify that the raw password matches the encoded password
        assert passwordEncoder.matches(rawPassword, encodedPassword);
    }
}