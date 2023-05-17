package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.contract.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl<User> implements UserService<User> {

    private static final String API_BASE_URL = "https://dummyjson.com/";

    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String url = API_BASE_URL + "users";
        ResponseEntity<User> response = restTemplate.getForEntity(url, (Class<User>) Object.class);
        User user = response.getBody();
        System.out.println("ll "+response.getBody()  );
        List<User> users= new ArrayList<>();
        users.add(user);
        return users;
    }

    @Override
    public User getUser(Integer userId) {
        String url = API_BASE_URL + "users/" + userId;
        ResponseEntity<User> response = restTemplate.getForEntity(url, (Class<User>) Object.class);
        return response.getBody();
    }

    @Override
    public List<User> searchUsers(String query) {
        String url = API_BASE_URL + "users/search?q=" + query;
//        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<User>>() {});
        ResponseEntity<User> response = restTemplate.getForEntity(url, (Class<User>) Object.class);
        User user = response.getBody();
        List<User> users= new ArrayList<>();
        users.add(user);
        return users;
    }
}