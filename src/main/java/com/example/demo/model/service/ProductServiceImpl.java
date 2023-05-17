package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Product;
import com.example.demo.model.contract.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl<Product, Category> implements ProductService<Product, Category> {

    private static final String API_BASE_URL = "https://dummyjson.com/";
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final RestTemplate restTemplate;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        String url = API_BASE_URL + "products";
        ResponseEntity<Product> response = restTemplate.getForEntity(url, (Class<Product>) Object.class);
        Product product = response.getBody();
        List<Product> products= new ArrayList<>();
        products.add(product);
        LOGGER.info("Retrieved all products: {}", products);
        return products;
    }

    @Override
    public List<Product> getAllProducts(int limit, int skip, String... fields) {
        String url = API_BASE_URL + "products?limit=" + limit + "&skip=" + skip;
        if (fields.length > 0) {
            String selectedFields = String.join(",", fields);
            url += "&select=" + selectedFields;
        }
        ResponseEntity<Product> response = restTemplate.getForEntity(url, (Class<Product>) Object.class);
        Product product = response.getBody();
        LOGGER.info("Retrieved products with limit={}, skip={}, fields={}: {}", limit, skip, Arrays.toString(fields), product);
        List<Product> products= new ArrayList<>();
        products.add(product);
        return products;
     
    }

    @Override
    public Product getProduct(Integer productId) {
        String url = API_BASE_URL + "products/" + productId;
        ResponseEntity<Product> response = restTemplate.getForEntity(url, (Class<Product>) Object.class);
        Product product = response.getBody();
        LOGGER.info("Retrieved product with productId={}: {}", productId, product);
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        String url = API_BASE_URL + "products/category/" + categoryName;
        ResponseEntity<Product> response = restTemplate.getForEntity(url, (Class<Product>) Object.class);
        Product prod = response.getBody();
        System.out.println("ll "+response.getBody()  );
        LOGGER.info("Retrieved products by category '{}': {}", categoryName, prod);
        List<Product> product= new ArrayList<>();
        product.add(prod);
        return product;
    }
    
    @Override
    public List<Product> searchProducts(String query) {
        String url = API_BASE_URL + "products/search?q=" + query;
        ResponseEntity<Product> response = restTemplate.getForEntity(url, (Class<Product>) Object.class);
        Product prod = response.getBody();
        System.out.println("ll "+response.getBody()  );
        List<Product> product= new ArrayList<>();
        product.add(prod);
        return product;
    }

    @Override
    public List<Category> getCategories() {
        String url = API_BASE_URL + "products/categories";
        ResponseEntity<Category> response = restTemplate.getForEntity(url, (Class<Category>) Object.class);
        Category category = response.getBody();
        System.out.println("ll "+response.getBody()  );
        List<Category> cart= new ArrayList<>();
        cart.add(category);
        return cart;
    }
}
