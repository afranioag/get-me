package com.aag.getme.service;

import com.aag.getme.dto.PersonDTO;
import com.aag.getme.exceptions.DatabaseException;
import com.aag.getme.exceptions.ModelNotFoundException;
import com.aag.getme.model.Person;
import com.aag.getme.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private static final String PERSON_NOT_FOUND = "Person not found with id: ";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public Person create(PersonDTO dto) {
        Person person = modelMapper.map(dto, Person.class);
        return personRepository.save(person);
    }

    @Transactional
    public PersonDTO update(PersonDTO dto, Long personId) {
        try {
            Person person = personRepository.getReferenceById(personId);
            modelMapper.map(dto, person);
            return modelMapper.map(personRepository.save(person), PersonDTO.class);
        }catch (EntityNotFoundException e) {
            throw new ModelNotFoundException(PERSON_NOT_FOUND + personId);
        }
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long personId) {

        Person person = personRepository.findById(personId).orElseThrow(() ->
                new ModelNotFoundException(PERSON_NOT_FOUND + personId));

        return modelMapper.map(person, PersonDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonDTO.class)).toList();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long personId) {
        if(personRepository.existsById(personId)) {
            try {
                personRepository.deleteById(personId);

            } catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Falha de integrida referencial");

            }
        } else {
            throw new ModelNotFoundException(PERSON_NOT_FOUND + personId);

        }
    }
}
