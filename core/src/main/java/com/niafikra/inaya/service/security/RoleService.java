package com.niafikra.inaya.service.security;

import com.niafikra.inaya.domain.security.Permission;
import com.niafikra.inaya.domain.security.Role;
import com.niafikra.inaya.repositories.security.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(String admin) {
        return roleRepository.findByName(admin);
    }

    public Role save(Role role) {
        return roleRepository.save(role);

    }

    @Transactional
    public List<Role> removePermissionFromRoles(Permission permission) {
        List<Role> roles = roleRepository.findAllByPermissions(permission);

        roles.forEach(role -> role.removePermission(permission));
        return roleRepository.saveAll(roles);
    }

    @Transactional
    public Role findOrCreateAdminRole(){
        Role adminRole = findRoleByName("admin");
        if(adminRole == null){
            adminRole = new Role();
            adminRole.setName("admin");
            adminRole = save(adminRole);
        }

        return adminRole;
    }
}
