package com.aag.getme.controller;

import com.aag.getme.dto.PersonDto;
import com.aag.getme.model.MyEntity;
import com.aag.getme.model.Person;
import com.aag.getme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(value = "/v1")
    public ResponseEntity<MyEntity> create(@RequestBody PersonDto dto) {

        Person person = personService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(uri).body(person);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping(value = "/{personId}/v1")
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto dto, @PathVariable Long personId) {
        return ResponseEntity.ok().body(personService.update(dto, personId));
    }

    @GetMapping(value = "/{personId}/v1")
    public ResponseEntity<PersonDto> findById(@PathVariable Long personId) {
        return ResponseEntity.ok().body(personService.findById(personId));
    }

    @GetMapping(value = "/v1")
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping(value = "/{personId}/v1")
    public ResponseEntity<Void> delete(@PathVariable Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }
}
