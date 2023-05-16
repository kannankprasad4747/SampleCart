package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImpl<T> implements CartService<T> {

	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	private static final String API_BASE_URL = "https://dummyjson.com/";

	private final RestTemplate restTemplate;

	public CartServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<T> getAllCarts() {
		String url = API_BASE_URL + "carts";
		logger.info("Getting all carts from URL: {}", url);
		T[] carts = restTemplate.getForObject(url, (Class<T[]>) Object[].class);
		return Arrays.asList(carts);
	}

	@Override
	public T getCart(Integer cartId) {
		String url = API_BASE_URL + "carts/" + cartId;
		logger.info("Getting cart with ID: {} from URL: {}", cartId, url);
		T cart = restTemplate.getForObject(url, (Class<T>) Object.class);
		return cart;
	}

	@Override
	public List<T> getUserCarts(Integer userId) {
		String url = API_BASE_URL + "carts/user/" + userId;
		logger.info("Getting carts for user with ID: {} from URL: {}", userId, url);
		T[] carts = restTemplate.getForObject(url, (Class<T[]>) Object[].class);
		return Arrays.asList(carts);
	}
}
