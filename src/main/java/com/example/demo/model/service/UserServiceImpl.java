package com.example.demo.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl<U> implements UserService<U> {

    private static final String API_BASE_URL = "https://dummyjson.com/";

    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<U> getAllUsers() {
        String url = API_BASE_URL + "users";
        U[] users = restTemplate.getForObject(url, (Class<U[]>) Object[].class);
        return Arrays.asList(users);
    }

    @Override
    public U getUser(Integer userId) {
        String url = API_BASE_URL + "users/" + userId;
        return restTemplate.getForObject(url, (Class<U>) Object.class);
    }

    @Override
    public List<U> searchUsers(String query) {
        String url = API_BASE_URL + "users/search?q=" + query;
        U[] users = restTemplate.getForObject(url, (Class<U[]>) Object[].class);
        return Arrays.asList(users);
    }
}
