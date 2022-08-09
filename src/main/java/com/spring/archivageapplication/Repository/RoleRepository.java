package com.spring.archivageapplication.Repository;

import com.spring.archivageapplication.Models.Role;
import com.spring.archivageapplication.Models.RoleEn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEn name);
}
