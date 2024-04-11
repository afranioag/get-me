package com.aag.getme.service;

import com.aag.getme.dto.UserDto;
import com.aag.getme.exceptions.DatabaseException;
import com.aag.getme.exceptions.ModelNotFoundException;
import com.aag.getme.model.Role;
import com.aag.getme.model.User;
import com.aag.getme.projections.UserDetailsProjection;
import com.aag.getme.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User not found with id: ";
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(encoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public UserDto update(UserDto dto, Long userId) {
        try {
            User user = userRepository.getReferenceById(userId);
            modelMapper.map(dto, user);
            modelMapper.map(dto, user);
            user.setPassword(encoder.encode(dto.getPassword()));
            return modelMapper.map(userRepository.save(user), UserDto.class);
        }catch (EntityNotFoundException e) {
            throw new ModelNotFoundException(USER_NOT_FOUND + userId);
        }

    }

    @Transactional(readOnly = true)
    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException(USER_NOT_FOUND + userId));

        return modelMapper.map(user, UserDto.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long userId) {
        if(userRepository.existsById(userId)) {
           try {
               userRepository.deleteById(userId);
           } catch (DataIntegrityViolationException e) {
               throw new DatabaseException("Falha de integrida referencial");
           }
        }

        throw new ModelNotFoundException(USER_NOT_FOUND + userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
}
