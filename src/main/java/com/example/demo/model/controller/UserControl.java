package com.example.demo.model.controller;

import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService<User> userService;

	@Autowired
	public UserController(UserService<User> userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		logger.info("Fetching all users");
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Integer userId) {
		logger.info("Fetching user with ID: {}", userId);
		return userService.getUser(userId);
	}

	@GetMapping("/search/{query}")
	public List<User> searchUsers(@PathVariable String query) {
		logger.info("Searching users with query: {}", query);
		return userService.searchUsers(query);
	}
}
