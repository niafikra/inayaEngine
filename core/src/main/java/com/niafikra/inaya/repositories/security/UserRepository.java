package com.niafikra.inaya.repositories.security;

import com.niafikra.inaya.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsernameIgnoreCase(String loginName);
}
