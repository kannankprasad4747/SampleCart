package com.example.demo;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartService<Cart> cartService;

    private CartServiceImpl cartServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartServiceImpl = new CartServiceImpl(cartService);
    }

    @Test
    void getAllCarts_shouldReturnListOfCarts() {
        // Arrange
        Cart cart1 = new Cart(1, 100.0);
        Cart cart2 = new Cart(2, 200.0);
        List<Cart> carts = Arrays.asList(cart1, cart2);

        when(cartService.getAllCarts()).thenReturn(carts);

        // Act
        List<Cart> result = cartServiceImpl.getAllCarts();

        // Assert
        assertEquals(carts, result);
        verify(cartService, times(1)).getAllCarts();
    }

    @Test
    void getCart_shouldReturnCartById() {
        // Arrange
        int cartId = 1;
        Cart cart = new Cart(cartId, 100.0);

        when(cartService.getCart(cartId)).thenReturn(cart);

        // Act
        Cart result = cartServiceImpl.getCart(cartId);

        // Assert
        assertEquals(cart, result);
        verify(cartService, times(1)).getCart(cartId);
    }

    @Test
    void getUserCarts_shouldReturnListOfCartsByUserId() {
        // Arrange
        int userId = 1;
        Cart cart1 = new Cart(1, 100.0);
        Cart cart2 = new Cart(2, 200.0);
        List<Cart> userCarts = Arrays.asList(cart1, cart2);

        when(cartService.getUserCarts(userId)).thenReturn(userCarts);

        // Act
        List<Cart> result = cartServiceImpl.getUserCarts(userId);

        // Assert
        assertEquals(userCarts, result);
        verify(cartService, times(1)).getUserCarts(userId);
    }
}
