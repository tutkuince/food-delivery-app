package com.incetutku.userservice.service;

import com.incetutku.userservice.dto.UserDTO;
import com.incetutku.userservice.entity.User;
import com.incetutku.userservice.mapper.UserMapper;
import com.incetutku.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }

    public UserDTO getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserMapper.INSTANCE::mapUserToUserDTO).orElse(null);
    }
}
