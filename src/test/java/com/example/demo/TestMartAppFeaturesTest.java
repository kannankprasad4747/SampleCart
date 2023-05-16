package com.example.demo;

import com.example.demo.app.AbstractTestMartAppFeatures;
import com.example.demo.app.impl.TestMartAppFeatures;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestMartAppFeaturesTest {

    @Mock
    private ProductService<Product, Category> productService;

    @Mock
    private CartService<Cart> cartService;

    private AbstractTestMartAppFeatures testMartAppFeatures;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testMartAppFeatures = new TestMartAppFeatures(productService, cartService);
    }

    @Test
    void getProductTitlesByWorseRating_shouldPrintTitlesWithLowerOrEqualRating() {
        // Arrange
        double rating = 3.5;
        Product product1 = new Product(1, "Product 1", 4.0);
        Product product2 = new Product(2, "Product 2", 3.0);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        // Act
        testMartAppFeatures.getProductTitlesByWorseRating(rating);

        // Assert
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getCartWithHighestTotal_shouldReturnCartWithHighestTotal() {
        // Arrange
        Cart cart1 = new Cart(1, 100.0);
        Cart cart2 = new Cart(2, 200.0);
        List<Cart> carts = Arrays.asList(cart1, cart2);

        when(cartService.getAllCarts()).thenReturn(carts);

        // Act
        Cart result = testMartAppFeatures.getCartWithHighestTotal();

        // Assert
        assertEquals(cart2, result);
        verify(cartService, times(1)).getAllCarts();
    }

    @Test
    void getCartWithLowestTotal_shouldReturnCartWithLowestTotal() {
        // Arrange
        Cart cart1 = new Cart(1, 100.0);
        Cart cart2 = new Cart(2, 200.0);
        List<Cart> carts = Arrays.asList(cart1, cart2);

        when(cartService.getAllCarts()).thenReturn(carts);

        // Act
        Cart result = testMartAppFeatures.getCartWithLowestTotal();

        // Assert
        assertEquals(cart1, result);
        verify(cartService, times(1)).getAllCarts();
    }

    @Test
    void addProductImagesToUserCart_shouldEnrichProductInformation() {
        // Arrange
        int userId = 1;
        Cart cart = new Cart(1, 100.0);
        List<Product> cartProducts = Arrays.asList(
                new Product(1, "Product 1", 4.0),
                new Product(2, "Product 2", 3.0)
        );
        cart.setProducts(cartProducts);

        when(cartService.getUserCarts(userId)).thenReturn(Arrays.asList(cart));
        when(productService.getProduct(1)).thenReturn(new Product(1, "Product 1", 4.0, "image1.jpg"));
        when(productService.getProduct(2)).thenReturn(new Product(2, "Product 2", 3.0, "
