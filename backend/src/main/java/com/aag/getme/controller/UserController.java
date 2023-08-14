package com.aag.getme.controller;

import com.aag.getme.dto.UserDto;
import com.aag.getme.model.MyEntity;
import com.aag.getme.model.User;
import com.aag.getme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/v1")
    public ResponseEntity<MyEntity> create(@RequestBody UserDto dto) {

        User user = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping(value = "/{userId}/v1")
    public ResponseEntity<UserDto> update(@RequestBody UserDto dto, @PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.update(dto, userId));
    }

    @GetMapping(value = "/{userId}/v1")
    public ResponseEntity<UserDto> findById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @DeleteMapping(value = "/{userId}/v1")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
