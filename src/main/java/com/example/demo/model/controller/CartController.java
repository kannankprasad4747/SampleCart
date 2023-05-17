package com.example.demo.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.contract.CartService;

import java.util.LinkedHashMap;
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
        Object response = cartService.getCart(cartId);

        // Check if the response is a LinkedHashMap
        if (response instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> cartMap = (LinkedHashMap<?, ?>) response;

            // Retrieve the properties from the cartMap
            int id = (Integer) cartMap.get("id");
            List<Product> products = (List<Product>) cartMap.get("products");
            double total = (Double.valueOf( (int)cartMap.get("total")));

            // Create a new Cart object using the retrieved properties
            Cart cart = new Cart(id, products, total);

            return cart;
        }

        return null; // or throw an exception if needed
    }

	@GetMapping("/user/{userId}")
	public List<Cart> getUserCarts(@PathVariable Integer userId) {
		logger.info("Fetching carts for user with ID: {}", userId);
		return cartService.getUserCarts(userId);
	}
}
