package org.eulu.bookshop.repository;

import java.util.Optional;
import org.eulu.bookshop.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUsersByEmail(String email);
}
