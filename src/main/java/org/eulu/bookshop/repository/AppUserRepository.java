package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<User, Long> {
    Optional<User> findAppUsersByEmail(String email);
}
