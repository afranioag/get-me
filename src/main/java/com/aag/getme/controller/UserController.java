package com.aag.getme.controller;

import com.aag.getme.dto.UserDTO;
import com.aag.getme.model.User;
import com.aag.getme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/v1")
    public ResponseEntity<?> create(@RequestBody UserDTO dto) {
        User user = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{userId}/v1")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.update(dto, userId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{userId}/v1")
    public ResponseEntity<UserDTO> findById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{userId}/v1")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
