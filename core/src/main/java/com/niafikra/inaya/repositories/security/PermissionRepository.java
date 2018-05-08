package com.niafikra.inaya.repositories.security;

import com.niafikra.inaya.domain.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findAllBySync(boolean sync);
}
