package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl<U> implements UserService<U> {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String API_BASE_URL = "https://dummyjson.com/";

	private final RestTemplate restTemplate;

	public UserServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<U> getAllUsers() {
		String url = API_BASE_URL + "users";
		logger.info("Getting all users from URL: {}", url);
		U[] users = restTemplate.getForObject(url, (Class<U[]>) Object[].class);
		return Arrays.asList(users);
	}

	@Override
	public U getUser(Integer userId) {
		String url = API_BASE_URL + "users/" + userId;
		logger.info("Getting user with ID: {} from URL: {}", userId, url);
		return restTemplate.getForObject(url, (Class<U>) Object.class);
	}

	@Override
	public List<U> searchUsers(String query) {
		String url = API_BASE_URL + "users/search?q=" + query;
		logger.info("Searching users with query: {} from URL: {}", query, url);
		U[] users = restTemplate.getForObject(url, (Class<U[]>) Object[].class);
		return Arrays.asList(users);
	}

	// Implement other methods...

}
