package com.javastudio.reflection.service;

import com.javastudio.reflection.annotations.Inject;
import com.javastudio.reflection.repository.PersonRepository;

public class PersonService {
    private final PersonRepository personRepository;

    @Inject
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
