package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.model.contract.UserService;
import com.example.demo.model.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService<User> userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User(1, "John", "john@example.com", "password"),
                new User(2, "Jane", "jane@example.com", "password")
        );

        when(userService.getAllUsers()).thenReturn(users);

        List<User> response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(users, response);

        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void testGetUser_NotFound() {
        int userId = 1;

        when(userService.getUser(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() == null);

        verify(userService, times(1)).getUser(userId);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testSearchUsers() {
        String query = "John";
        List<User> users = Arrays.asList(
                new User(1, "John", "john@example.com", "password"),
                new User(2, "Johnny", "johnny@example.com", "password")
        );

        when(userService.searchUsers(query)).thenReturn(users);

        List<User> response = userController.searchUsers(query);

        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(users, response);

        verify(userService, times(1)).searchUsers(query);
        verifyNoMoreInteractions(userService);
    }
}
