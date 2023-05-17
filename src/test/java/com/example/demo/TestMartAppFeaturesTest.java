package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.example.demo.app.impl.TestMartAppFeatures;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.contract.CartService;
import com.example.demo.model.contract.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestMartAppFeaturesTest {

    @Mock
    private ProductService<Product, Category> productService;

    @Mock
    private CartService<Cart> cartService;

    @Mock
    private Logger logger;

    private TestMartAppFeatures testMartAppFeatures;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testMartAppFeatures = new TestMartAppFeatures(productService, cartService);
        testMartAppFeatures.logger=logger;
    }

    @Test
    void getProductTitlesByWorseRating_ShouldPrintProductTitlesWithLowerOrEqualRating() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Product 1", 4.5, null));
        products.add(new Product(2, "Product 2", 3.0, null));
        products.add(new Product(3, "Product 3", 2.5, null));

        when(productService.getAllProducts()).thenReturn(products);

        testMartAppFeatures.getProductTitlesByWorseRating(3.0);

        // Verify that the logger.info() method was called with the expected product titles
        verify(logger, times(2)).info(anyString(), anyString());
        verify(productService).getAllProducts();
    }

    @Test
    void getCartWithHighestTotal_ShouldReturnCartWithHighestTotal() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, null, 100.0));
        carts.add(new Cart(2, null, 50.0));
        carts.add(new Cart(3, null, 75.0));

        when(cartService.getAllCarts()).thenReturn(carts);

        Cart expectedCart = new Cart(1, null, 100.0);
        Cart actualCart = testMartAppFeatures.getCartWithHighestTotal();

        verify(logger).info(anyString(), any(Cart.class));
        verify(cartService).getAllCarts();
    }

    @Test
    void getCartWithLowestTotal_ShouldReturnCartWithLowestTotal() {
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart(1, null, 100.0));
        carts.add(new Cart(2, null, 50.0));
        carts.add(new Cart(3, null, 75.0));

        when(cartService.getAllCarts()).thenReturn(carts);

        Cart expectedCart = new Cart(2, null, 50.0);
        Cart actualCart = testMartAppFeatures.getCartWithLowestTotal();

        verify(logger).info(anyString(), any(Cart.class));
        verify(cartService).getAllCarts();
    }

    @Test
    void addProductImagesToUserCart_ShouldAddImagesToCartProducts() {
        Integer userId = 1;
        List<Cart> userCarts = new ArrayList<>();
        Cart cart = new Cart(1, null, 0.0);
        List<Product> cartProducts= new ArrayList<>();
        cartProducts.add(new Product(1, "Product 1", 0.0, null));
        cartProducts.add(new Product(2, "Product 2", 0.0, null));
        cart.setProducts(cartProducts);
        userCarts.add(cart);
        when(cartService.getUserCarts(userId)).thenReturn(userCarts);

        Product product1 = new Product(1, "Product 1", 0.0, null);
        product1.setImages(List.of("image1.jpg", "image2.jpg"));
        Product product2 = new Product(2, "Product 2", 0.0, null);
        product2.setImages(List.of("image3.jpg", "image4.jpg"));
        when(productService.getProduct(1)).thenReturn(product1);
        when(productService.getProduct(2)).thenReturn(product2);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        List<Product> actualProducts = testMartAppFeatures.addProductImagesToUserCart(userId);

        verify(cartService).getUserCarts(userId);
        verify(productService, times(2)).getProduct(anyInt());
    }

}
