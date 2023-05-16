package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductService<Product, Category> productService;

    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productServiceImpl = new ProductServiceImpl(productService);
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        // Arrange
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 20.0);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        // Act
        List<Product> result = productServiceImpl.getAllProducts();

        // Assert
        assertEquals(products, result);
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProduct_shouldReturnProductById() {
        // Arrange
        int productId = 1;
        Product product = new Product(productId, "Product 1", 10.0);

        when(productService.getProduct(productId)).thenReturn(product);

        // Act
        Product result = productServiceImpl.getProduct(productId);

        // Assert
        assertEquals(product, result);
        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    void searchProducts_shouldReturnListOfProductsByQuery() {
        // Arrange
        String query = "search";
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 20.0);
        List<Product> searchedProducts = Arrays.asList(product1, product2);

        when(productService.searchProducts(query)).thenReturn(searchedProducts);

        // Act
        List<Product> result = productServiceImpl.searchProducts(query);

        // Assert
        assertEquals(searchedProducts, result);
        verify(productService, times(1)).searchProducts(query);
    }

    @Test
    void getCategories_shouldReturnListOfCategories() {
        // Arrange
        Category category1 = new Category(1, "Category 1");
        Category category2 = new Category(2, "Category 2");
        List<Category> categories = Arrays.asList(category1, category2);

        when(productService.getCategories()).thenReturn(categories);

        // Act
        List<Category> result = productServiceImpl.getCategories();

        // Assert
        assertEquals(categories, result);
        verify(productService, times(1)).getCategories();
    }

    @Test
    void getProductsByCategory_shouldReturnListOfProductsByCategoryName() {
        // Arrange
        String categoryName = "Category 1";
        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 20.0);
        List<Product> productsByCategory = Arrays.asList(product1, product2);

        when(productService.getProductsByCategory(categoryName)).thenReturn(productsByCategory);

        // Act
        List<Product> result = productServiceImpl.getProductsByCategory(categoryName);

        // Assert
        assertEquals(productsByCategory, result);
        verify(productService, times(1)).getProductsByCategory(categoryName);
    }
}
