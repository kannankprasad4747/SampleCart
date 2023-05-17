package com.example.demo;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.contract.ProductService;
import com.example.demo.model.controller.ProductController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService<Product, Category> productService;

    @Mock
    private Logger logger;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
        productController.logger=logger;
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        // Arrange
        List<Product> expectedProducts = Arrays.asList(
                new Product(1, "Product 1", 4.5, Arrays.asList("image1.jpg", "image2.jpg")),
                new Product(2, "Product 2", 3.8, Arrays.asList("image3.jpg", "image4.jpg"))
        );

        when(productService.getAllProducts()).thenReturn(expectedProducts);

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertEquals(expectedProducts, result);
        verify(logger, times(1)).info("Fetching all products");
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProduct_shouldReturnProductById() {
        // Arrange
        Integer productId = 1;
        Product expectedProduct = new Product(1, "Product 1", 4.5, Arrays.asList("image1.jpg", "image2.jpg"));

        when(productService.getProduct(productId)).thenReturn(expectedProduct);

        // Act
        Product result = productController.getProduct(productId);

        // Assert
        verify(logger, times(1)).info("Fetching product with ID: {}", productId);
        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    void getCategories_shouldReturnListOfCategories() {
        // Arrange
        List<Category> expectedCategories = Arrays.asList(
                new Category(1, "Category 1"),
                new Category(2, "Category 2")
        );

        when(productService.getCategories()).thenReturn(expectedCategories);

        // Act
        List<Category> result = productController.getCategories();

        // Assert
        assertEquals(expectedCategories, result);
        verify(logger, times(1)).info("Fetching all categories");
        verify(productService, times(1)).getCategories();
    }

    @Test
    void getProductsByCategory_shouldReturnListOfProductsByCategory() {
        // Arrange
        String categoryName = "category";
        List<Product> expectedProducts = Arrays.asList(
                new Product(1, "Product 1", 4.5, Arrays.asList("image1.jpg", "image2.jpg")),
                new Product(2, "Product 2", 3.8, Arrays.asList("image3.jpg", "image4.jpg"))
        );

        when(productService.getProductsByCategory(categoryName)).thenReturn(expectedProducts);

        // Act
        List<Product> result = productController.getProductsByCategory(categoryName);

        // Assert
        assertEquals(expectedProducts, result);
        verify(logger, times(1)).info("Fetching products for category: {}", categoryName);
        verify(productService, times(1)).getProductsByCategory(categoryName);
    }
}
