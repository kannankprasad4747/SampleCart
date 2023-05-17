package com.example.demo;


import com.example.demo.model.Cart;
import com.example.demo.model.contract.CartService;
import com.example.demo.model.controller.CartController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService<Cart> cartService;

    @Mock
    private Logger logger;

    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartController = new CartController(cartService);
    }

    @Test
    void getAllCarts_ShouldReturnAllCarts() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, new ArrayList<>(), 100.0));
        carts.add(new Cart(2, new ArrayList<>(), 200.0));
        when(cartService.getAllCarts()).thenReturn(carts);

        List<Cart> result = cartController.getAllCarts();

        assertEquals(carts, result);
        verify(cartService).getAllCarts();
    }

    @Test
    void getCart_ShouldReturnCartById() {
        // Arrange
        int cartId = 1;
        Cart expectedCart = new Cart(1, new ArrayList<>(), 0.0);
        when(cartService.getCart(cartId)).thenReturn(expectedCart);

        // Act
        Cart actualCart = cartController.getCart(cartId);

        // Assert
        verify(cartService).getCart(cartId);
    }

    @Test
    void getUserCarts_ShouldReturnUserCarts() {
        int userId = 1;
        List<Cart> userCarts = new ArrayList<>();
        userCarts.add(new Cart(1, new ArrayList<>(), 100.0));
        userCarts.add(new Cart(2, new ArrayList<>(), 200.0));
        when(cartService.getUserCarts(userId)).thenReturn(userCarts);

        List<Cart> result = cartController.getUserCarts(userId);

        assertEquals(userCarts, result);
        verify(cartService).getUserCarts(userId);
    }
}
