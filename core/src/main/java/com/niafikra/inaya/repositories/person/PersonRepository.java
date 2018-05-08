package com.niafikra.inaya.repositories.person;

import com.niafikra.inaya.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
