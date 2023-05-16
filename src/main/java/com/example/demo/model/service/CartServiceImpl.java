package com.example.demo.model.service;



import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl<T> implements CartService<T> {

    private static final String API_BASE_URL = "https://dummyjson.com/";

    private final RestTemplate restTemplate;

    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<T> getAllCarts() {
        String url = API_BASE_URL + "carts";
        T[] carts = restTemplate.getForObject(url, (Class<T[]>) Object[].class);
        return Arrays.asList(carts);
    }

    @Override
    public T getCart(Integer cartId) {
        String url = API_BASE_URL + "carts/" + cartId;
        T cart = restTemplate.getForObject(url, (Class<T>) Object.class);
        return cart;
    }

    @Override
    public List<T> getUserCarts(Integer userId) {
        String url = API_BASE_URL + "carts/user/" + userId;
        T[] carts = restTemplate.getForObject(url, (Class<T[]>) Object[].class);
        return Arrays.asList(carts);
    }
}
