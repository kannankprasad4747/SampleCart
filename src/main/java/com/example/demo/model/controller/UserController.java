package com.example.demo.model.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.contract.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final com.example.demo.model.contract.UserService<User> userService;

	@Autowired
	public UserController(UserService<User> userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		logger.info("Fetching all users");
		return userService.getAllUsers();
	}

	  @GetMapping("/{id}")
	    public ResponseEntity<User> getUser(@PathVariable("id") Integer userId) {
	        // Logic to fetch the user from the service
		  System.out.println("kp");
		 Object response =userService.getUser(userId);
		 String address="";
		 User user=null;
		  if (response instanceof LinkedHashMap) {
	            LinkedHashMap<?, ?> userMap = (LinkedHashMap<?, ?>) response;

	            // Retrieve the properties from the cartMap
	            int id = (Integer) userMap.get("id");
	            String name=  (String) userMap.get("firstName");
	            String email=  (String) userMap.get("email");
	            System.out.println("kp "+ userMap.get("address"));
	            if(userMap.get("address") instanceof LinkedHashMap<?,?>) {
	            	 LinkedHashMap<?, ?> addressMap = (LinkedHashMap<?, ?>) userMap.get("address");
	            	 address= (String) addressMap.get("address");
	            }
	            userMap.get("address");

	            // Create a new Cart object using the retrieved properties
	             user = new User(id, name, email,address);

	        }
		;
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	@GetMapping("/search/{query}")
	public List<User> searchUsers(@PathVariable String query) {
		logger.info("Searching users with query: {}", query);
		System.out.println("ww");
		return userService.searchUsers(query);
	}
}
