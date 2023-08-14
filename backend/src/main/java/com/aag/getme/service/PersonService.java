package com.aag.getme.service;

import com.aag.getme.dto.PersonDto;
import com.aag.getme.model.Person;
import com.aag.getme.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private static final String PERSON_NOT_FOUND = "Person not found with id: ";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonRepository personRepository;

    public Person create(PersonDto dto) {

        Person person = modelMapper.map(dto, Person.class);
        return personRepository.save(person);
    }

    public PersonDto update(PersonDto dto, Long personId) {

        Person person = personRepository.findById(personId).orElseThrow(() ->
                new RuntimeException(PERSON_NOT_FOUND + personId));

        modelMapper.map(dto, person);
        return modelMapper.map(personRepository.save(person), PersonDto.class);
    }

    public PersonDto findById(Long personId) {

        Person person = personRepository.findById(personId).orElseThrow(() ->
                new RuntimeException(PERSON_NOT_FOUND + personId));

        return modelMapper.map(person, PersonDto.class);
    }

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonDto.class)).toList();
    }

    public void delete(Long personId) {

        Person person = personRepository.findById(personId).orElseThrow(() ->
                new RuntimeException(PERSON_NOT_FOUND + personId));

        personRepository.delete(person);
    }
}
