package com.example.demo;
import com.example.demo.model.contract.CartService;
import com.example.demo.model.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private CartService<Object> cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartServiceImpl<>(restTemplate);
    }

    @Test
    void getAllCarts_shouldReturnListOfCarts() {
        // Arrange
        String url = "https://dummyjson.com/carts";
        List<Object> expectedCarts = Arrays.asList(new Object(), new Object());
        ResponseEntity<List<Object>> responseEntity = new ResponseEntity<>(expectedCarts, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);
    }

    @Test
    void getCart_shouldReturnCartById() {
        // Arrange
        Integer cartId = 1;
        String url = "https://dummyjson.com/carts/" + cartId;
        Object expectedCart = new Object();

        when(restTemplate.getForObject(eq(url), eq(Object.class))).thenReturn(expectedCart);

        // Act
        Object result = cartService.getCart(cartId);

        // Assert
        assertEquals(expectedCart, result);
        verify(restTemplate, times(1)).getForObject(eq(url), eq(Object.class));
    }

    @Test
    void getUserCarts_shouldReturnListOfCartsByUserId() {
        // Arrange
        Integer userId = 1;
        String url = "https://dummyjson.com/carts/user/" + userId;
        List<Object> expectedCarts = Arrays.asList(new Object(), new Object());
        ResponseEntity<List<Object>> responseEntity = new ResponseEntity<>(expectedCarts, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

    }
}
