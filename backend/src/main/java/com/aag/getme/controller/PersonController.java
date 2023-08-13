package com.aag.getme.controller;

import com.aag.getme.dto.PersonDto;
import com.aag.getme.model.MyEntity;
import com.aag.getme.model.Person;
import com.aag.getme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/v1")
    public ResponseEntity<MyEntity> create(@RequestBody PersonDto dto) {
        Person person = personService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(uri).body(person);
    }
}
