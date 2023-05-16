package com.example.demo.model.controller;

import com.example.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	private final CartService<Cart> cartService;

	public CartController(CartService<Cart> cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public List<Cart> getAllCarts() {
		logger.info("Fetching all carts");
		return cartService.getAllCarts();
	}

	@GetMapping("/{cartId}")
	public Cart getCart(@PathVariable Integer cartId) {
		logger.info("Fetching cart with ID: {}", cartId);
		return cartService.getCart(cartId);
	}

	@GetMapping("/user/{userId}")
	public List<Cart> getUserCarts(@PathVariable Integer userId) {
		logger.info("Fetching carts for user with ID: {}", userId);
		return cartService.getUserCarts(userId);
	}
}
