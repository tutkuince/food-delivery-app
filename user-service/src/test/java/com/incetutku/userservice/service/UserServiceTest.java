package com.incetutku.userservice.service;

import com.incetutku.userservice.dto.UserDTO;
import com.incetutku.userservice.entity.User;
import com.incetutku.userservice.mapper.UserMapper;
import com.incetutku.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Create a mock user to be saved
        UserDTO mockUserDTO = new UserDTO(1, "User - 1", "Password", "Address - 1", "City - 1");
        User mockUser = UserMapper.INSTANCE.mapUserDTOToUser(mockUserDTO);

        // Mock the repository behaviour
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        // Call the service method
        UserDTO userDTO = userService.createUser(mockUserDTO);

        // Verify the result
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testGetUserById_ExistingId() {
        // Create a mock user ID
        int mockUserId = 1;

        // Create a mock user to be returned by the repository
        User mockUser = new User(mockUserId, "User - 1", "Password", "Address - 1", "City - 1");

        // Mock the repository behavior
        when(userRepository.findById(mockUserId)).thenReturn(Optional.of(mockUser));

        // Call the service method
        UserDTO userDTO = userService.getUserById(mockUserId);

        // Verify the result
        assertEquals(mockUserId, mockUser.getUserId());

        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(mockUserId);
    }

    @Test
    void testGetUserById_NonExistingId() {
        // Create a mock user ID
        int mockUserId = 1;

        // Mock the repository behavior
        when(userRepository.findById(mockUserId)).thenReturn(Optional.empty());

        // Call the service method
        UserDTO userDTO = userService.getUserById(mockUserId);

        // Verify the result
        assertNull(userDTO);

        // Verify that the repository method was called
        verify(userRepository, times(1)).findById(mockUserId);
    }
}