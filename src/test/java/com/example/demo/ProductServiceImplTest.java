package com.example.demo;
import com.example.demo.model.contract.ProductService;
import com.example.demo.model.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest<P, C> {

    @Mock
    private RestTemplate restTemplate;

    private ProductService<P, C> productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl<>(restTemplate);
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        // Arrange
        String url = "https://dummyjson.com/products";
        List<P> products = Arrays.asList(/* create some dummy products */);
        ResponseEntity<List<P>> response = new ResponseEntity<>(products, HttpStatus.OK);

        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class)))
                .thenReturn(response);
    }

}
