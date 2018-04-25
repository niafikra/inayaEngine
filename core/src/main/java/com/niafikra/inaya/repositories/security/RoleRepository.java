package com.niafikra.inaya.repositories.security;

import com.niafikra.inaya.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
