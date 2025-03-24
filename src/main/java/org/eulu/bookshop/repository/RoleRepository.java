package org.eulu.bookshop.repository;

import org.eulu.bookshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
<<<<<<< HEAD
    Role findByName(Role.RoleName name);
=======
>>>>>>> 10eda71 (Add Role entity)
}
