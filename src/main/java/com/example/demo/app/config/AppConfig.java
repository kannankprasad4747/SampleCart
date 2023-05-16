package com.example.demo.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
