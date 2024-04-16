package com.aag.getme.controller;

import com.aag.getme.dto.PersonDTO;
import com.aag.getme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{personId}/v1")
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO dto, @PathVariable Long personId) {
        return ResponseEntity.ok().body(personService.update(dto, personId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{personId}/v1")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long personId) {
        return ResponseEntity.ok().body(personService.findById(personId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/v1")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok().body(personService.findAll());
    }

}
