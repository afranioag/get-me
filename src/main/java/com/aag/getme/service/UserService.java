package com.aag.getme.service;

import com.aag.getme.dto.UserDTO;
import com.aag.getme.dto.UserResponse;
import com.aag.getme.exceptions.DatabaseException;
import com.aag.getme.exceptions.InvalidResourceAccessException;
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

import static com.aag.getme.config.auth.AppConfig.getUserNameAuthenticated;

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
    public UserResponse create(UserDTO dto) {
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new InvalidResourceAccessException("Senhas não são iguais!");

        User user = modelMapper.map(dto, User.class);
        user.setPassword(encoder.encode(dto.getPassword()));

        Role role = new Role();
        role.setId(2L);
        user.addRole(role);

        userRepository.save(user);

        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserDTO update(UserDTO dto, Long userId) {
        try {
            validatedUser(userId);

            User user = userRepository.getReferenceById(userId);
            modelMapper.map(dto, user);
            modelMapper.map(dto, user);
            user.setPassword(encoder.encode(dto.getPassword()));
            return modelMapper.map(userRepository.save(user), UserDTO.class);

        } catch (EntityNotFoundException e) {
            throw new ModelNotFoundException(USER_NOT_FOUND + userId);

        }

    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException(USER_NOT_FOUND + userId));

        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long userId) {
        if(!userRepository.existsById(userId))
            throw new ModelNotFoundException(USER_NOT_FOUND + userId);

        try {
            validatedUser(userId);
            userRepository.deleteById(userId);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");

        }
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

    public User getUserAuthenticated() {
        return userRepository.findByEmail(getUserNameAuthenticated());
    }

    private void validatedUser(Long userId) {
        User user = getUserAuthenticated();
        if(!userId.equals(user.getId()))
            throw new InvalidResourceAccessException("Usuário logado diferente do userId informado!");
    }
}
