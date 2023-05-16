package com.example.demo;
import com.example.demo.controller.CartController;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService<Cart> cartService;

    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartController = new CartController(cartService);
    }

    @Test
    void getAllCarts_shouldReturnListOfCarts() {
        // Arrange
        Cart cart1 = new Cart(1, 100.0);
        Cart cart2 = new Cart(2, 200.0);
        List<Cart> carts = Arrays.asList(cart1, cart2);

        when(cartService.getAllCarts()).thenReturn(carts);

        // Act
        ResponseEntity<List<Cart>> response = cartController.getAllCarts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
        verify(cartService, times(1)).getAllCarts();
    }

    @Test
    void getCart_shouldReturnCartById() {
        // Arrange
        int cartId = 1;
        Cart cart = new Cart(cartId, 100.0);

        when(cartService.getCart(cartId)).thenReturn(cart);

        // Act
        ResponseEntity<Cart> response = cartController.getCart(cartId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
        verify(cartService, times(1)).getCart(cartId);
    }
}
