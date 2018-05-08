package com.niafikra.inaya.repositories.security;

import com.niafikra.inaya.domain.security.Permission;
import com.niafikra.inaya.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAllByPermissions(Permission permission);

}
