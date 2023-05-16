package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserService<User> userService;

    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServiceImpl = new UserServiceImpl(userService);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // Arrange
        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        // Act
        List<User> result = userServiceImpl.getAllUsers();

        // Assert
        assertEquals(users, result);
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUser_shouldReturnUserById() {
        // Arrange
        int userId = 1;
        User user = new User(userId, "User 1");

        when(userService.getUser(userId)).thenReturn(user);

        // Act
        User result = userServiceImpl.getUser(userId);

        // Assert
        assertEquals(user, result);
        verify(userService, times(1)).getUser(userId);
    }

    @Test
    void searchUsers_shouldReturnListOfUsersByQuery() {
        // Arrange
        String query = "search";
        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");
        List<User> searchedUsers = Arrays.asList(user1, user2);

        when(userService.searchUsers(query)).thenReturn(searchedUsers);

        // Act
        List<User> result = userServiceImpl.searchUsers(query);

        // Assert
        assertEquals(searchedUsers, result);
        verify(userService, times(1)).searchUsers(query);
    }
}
