package com.example.demo.model.controller;

import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
		return productService.getProduct(productId);
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
