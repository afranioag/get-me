package com.aag.getme.service;

import com.aag.getme.dto.UserDto;
import com.aag.getme.model.User;
import com.aag.getme.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String USER_NOT_FOUND = "User not found with id: ";
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public User create(UserDto dto) {

        User user = modelMapper.map(dto, User.class);
        return userRepository.save(user);

    }

    public UserDto update(UserDto dto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException(USER_NOT_FOUND + userId));

        modelMapper.map(dto, user);
        return modelMapper.map(userRepository.save(user), UserDto.class);

    }

    public UserDto findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException(USER_NOT_FOUND + userId));

        return modelMapper.map(user, UserDto.class);
    }

    public void delete(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException(USER_NOT_FOUND + userId));

        userRepository.delete(user);
    }
}
