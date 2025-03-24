package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmailEqualsIgnoreCase(String email);

    Optional<User> findUserByEmailEqualsIgnoreCase(String email);
}
