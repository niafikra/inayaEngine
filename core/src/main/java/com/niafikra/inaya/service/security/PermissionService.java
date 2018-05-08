package com.niafikra.inaya.service.security;

import com.niafikra.inaya.annotations.Permissions;
import com.niafikra.inaya.domain.security.Permission;
import com.niafikra.inaya.domain.security.Role;
import com.niafikra.inaya.repositories.security.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleService roleService;

    @Transactional
    public void add(Permission permission) {
        Role adminRole = roleService.findOrCreateAdminRole();
        permission = permissionRepository.save(permission);

        adminRole.addPermission(permission);
        roleService.save(adminRole);
    }

    @Transactional
    public void delete(Permission permission) {
        roleService.removePermissionFromRoles(permission);
        permissionRepository.delete(permission);
    }

    @Transactional
    public void syncPermissions(String basePackage) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Permissions.class));
        List<String> permissionNames = provider.findCandidateComponents(basePackage).stream()
                .flatMap(beanDefinition -> {
                    try {
                        Class clazz = Class.forName(beanDefinition.getBeanClassName());
                        return Arrays.stream(clazz.getFields())
                                .map(field -> {
                                    try {
                                        return (String) field.get(null);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                        return null;
                                    }
                                });

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return Stream.empty();
                    }
                }).collect(Collectors.toList());

        log.info("Found " + permissionNames.size() + " permissions");
        syncPermissions(permissionNames);
    }

    @Transactional
    public void syncPermissions(Collection<String> permissionNames) {

        List<Permission> existingPermissions = permissionRepository.findAllBySync(true);
        List<String> existingPermissionNames = existingPermissions.stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toList());

        existingPermissions.stream()
                .filter(permission -> !permissionNames.contains(permission.getName()))
                .forEach(this::delete);

        permissionNames.stream()
                .filter(permissionName -> !existingPermissionNames.contains(permissionName))
                .map(Permission::new)
                .forEach(this::add);
    }




}
