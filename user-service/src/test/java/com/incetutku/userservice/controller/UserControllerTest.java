package com.incetutku.userservice.controller;

import com.incetutku.userservice.dto.UserDTO;
import com.incetutku.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById() {
        // Create a mock restaurant Id
        int mockUserId = 1;

        // Create a mock user to be saved
        UserDTO mockUser = new UserDTO(mockUserId, "User - 1", "Password", "Address - 1", "City - 1");

        // Mock the service behaviour
        when(userService.getUserById(mockUserId)).thenReturn(mockUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.getUserById(mockUserId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());

        // Verify that the service method was called
        verify(userService, times(1)).getUserById(mockUserId);
    }

    @Test
    void createUser() {
        // Create a mock user to be saved
        UserDTO mockUser = new UserDTO(1, "User - 1", "Password", "Address - 1", "City - 1");

        // Mock the service behaviour
        when(userService.createUser(mockUser)).thenReturn(mockUser);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.createUser(mockUser);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUser, response.getBody());

        // Verify that the service method was called
        verify(userService, times(1)).createUser(mockUser);
    }
}