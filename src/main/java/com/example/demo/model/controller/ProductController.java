package com.example.demo.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.contract.ProductService;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	public static  Logger logger = LoggerFactory.getLogger(ProductController.class);

	private final ProductService<Product, Category> productService;

	@Autowired
	public ProductController(ProductService<Product, Category> productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		logger.info("Fetching all products");
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable Integer productId) {
		logger.info("Fetching product with ID: {}", productId);
		Object response = productService.getProduct(productId);
		if(response instanceof LinkedHashMap<?,?>) {
			LinkedHashMap<?, ?> productMap = (LinkedHashMap<?, ?>) response;
		int id = 	(int) productMap.get("id");
		double rating = (Double) productMap.get("rating");
		String titile= (String)productMap.get("title");
		List<String> images = (List<String>) productMap.get("images");
		Product p = new Product(id,titile,rating,images);
		return p;
		}
		
		return null;
	}

	@GetMapping("/categories")
	public List<Category> getCategories() {
		logger.info("Fetching all categories");
		return productService.getCategories();
	}

	@GetMapping("/category/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable String categoryName) {
		logger.info("Fetching products for category: {}", categoryName);
		return productService.getProductsByCategory(categoryName);
	}
}
