package com.niafikra.inaya.app;

import com.niafikra.inaya.service.person.PersonService;
import com.niafikra.inaya.service.security.PermissionService;
import com.niafikra.inaya.service.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class Bootstrap {
    @Autowired
    UserService userService;
    @Autowired
    PersonService personService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void start() {
        log.info("Bootstrapping inaya ...");
        userService.createDefaultUserIfNeccesary();
        permissionService.syncPermissions("com.niafikra.inaya");
        log.info("Finished bootstrapping inaya");
    }

}
