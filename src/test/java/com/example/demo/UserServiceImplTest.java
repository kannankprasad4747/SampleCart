package com.example.demo;

import com.example.demo.model.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private UserServiceImpl<String> userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl<>(restTemplate);
    }

    @Test
    public void testGetAllUsers() {
        String apiUrl = "https://dummyjson.com/users";
        List<String> expectedUsers = Arrays.asList("User 1", "User 2", "User 3");

        ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(expectedUsers, HttpStatus.OK);
        when(restTemplate.exchange(apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {}))
                .thenReturn(responseEntity);
    }

    @Test
    public void testGetUser() {
        Integer userId = 1;
        String apiUrl = "https://dummyjson.com/users/" + userId;
        String expectedUser = "User 1";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedUser, HttpStatus.OK);
        when(restTemplate.getForEntity(apiUrl, String.class)).thenReturn(responseEntity);
    }

    @Test
    public void testSearchUsers() {
        String query = "John";
        String apiUrl = "https://dummyjson.com/users/search?q=" + query;
        List<String> expectedUsers = Arrays.asList("John Doe", "John Smith");

        ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(expectedUsers, HttpStatus.OK);
        when(restTemplate.exchange(apiUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {}))
                .thenReturn(responseEntity);

    }
}
