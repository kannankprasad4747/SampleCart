package com.example.demo.app.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.service.CartServiceImpl;
import com.example.demo.model.service.ProductServiceImpl;
import com.example.demo.model.service.UserServiceImpl;
import com.example.demo.model.*;
import com.example.demo.model.contract.*;
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UserService<User> userService(RestTemplate restTemplate) {
        return new UserServiceImpl<>(restTemplate);
    }
    
    @Bean
    public ProductService<Product, Category> productService(RestTemplate restTemplate) {
        return new ProductServiceImpl<>(restTemplate);
    }
    
    
    @Bean
    public CartService<Cart> cartService(RestTemplate restTemplate) {
        return new CartServiceImpl<>(restTemplate);
    }
}
