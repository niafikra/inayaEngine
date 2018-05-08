package com.niafikra.inaya.service.security;

import com.niafikra.inaya.domain.person.Person;
import com.niafikra.inaya.domain.security.User;
import com.niafikra.inaya.repositories.security.UserRepository;
import com.niafikra.inaya.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleService roleService;

    public User save(User user) {
        return userRepository.save(user);
    }

    public long count() {
        return userRepository.count();
    }

    @Transactional
    public boolean updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        return save(user) != null;
    }

    @Transactional
    public void createDefaultUserIfNeccesary() {
        if (count() != 0) return;

        Person person = personService.createDefaultPerson();

        User user = new User();
        user.setUsername("admin");
        setFreshUserFields(user);
        user.setPerson(person);

        user.getRoles().add(roleService.findOrCreateAdminRole());

        save(user);
        updatePassword(user, "admin");
    }

    private void setFreshUserFields(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setBlocked(false);
    }
}
