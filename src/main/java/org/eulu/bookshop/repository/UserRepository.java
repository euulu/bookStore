package org.eulu.bookshop.repository;

import org.eulu.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmailEqualsIgnoreCase(String email);
}
