package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import com.example.demo.model.Cart;
import com.example.demo.model.contract.CartService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceImpl<Cart> implements CartService<Cart> {

    private static final String API_BASE_URL = "https://dummyjson.com/";

    private final RestTemplate restTemplate;

    public CartServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Cart> getAllCarts() {
        String url = API_BASE_URL + "carts";
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, (Class<Cart>) Object.class);
        Cart crt = response.getBody();
        System.out.println("ll "+response.getBody()  );
        List<Cart> cart= new ArrayList<>();
        cart.add(crt);
        return cart;
    }

    @Override
    public Cart getCart(Integer cartId) {
        String url = API_BASE_URL + "carts/" + cartId;
        return restTemplate.getForObject(url, (Class<Cart>) Object.class);
    }

    @Override
    public List<Cart> getUserCarts(Integer userId) {
        String url = API_BASE_URL + "carts/user/" + userId;
//        ResponseEntity<List<Cart>> response = restTemplate.exchange(url, HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Cart>>() {});
//        return response.getBody();
        ResponseEntity<Cart> response = restTemplate.getForEntity(url, (Class<Cart>) Object.class);
        Cart crt = response.getBody();
        System.out.println("ll "+response.getBody()  );
        List<Cart> cart= new ArrayList<>();
        cart.add(crt);
        return cart;
    }
}