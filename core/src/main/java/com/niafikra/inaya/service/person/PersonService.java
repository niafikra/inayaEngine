package com.niafikra.inaya.service.person;

import com.niafikra.inaya.domain.person.Person;
import com.niafikra.inaya.repositories.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person createDefaultPerson() {
        Person person = new Person();
        person.setFirstName("admin");
        person.setMiddleName("admin");
        person.setSurname("admin");
        save(person);

        return person;
    }
}
