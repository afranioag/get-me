package com.aag.getme.service;

import com.aag.getme.dto.PersonDto;
import com.aag.getme.model.Person;
import com.aag.getme.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(PersonDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setCpf(dto.getCpf());
        person.setEmail(dto.getEmail());
        person.setTelefone(dto.getTelefone());

        return personRepository.save(person);
    }

}
