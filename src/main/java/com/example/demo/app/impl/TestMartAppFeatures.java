package com.example.demo.app.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class TestMartAppFeatures extends AbstractTestMartAppFeatures {

	private static final Logger logger = LoggerFactory.getLogger(TestMartAppFeatures.class);

	private final ProductService<Product, Category> productService;
	private final CartService<Cart> cartService;

	@Autowired
	public TestMartAppFeatures(ProductService<Product, Category> productService, CartService<Cart> cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}

	@Override
	public void getProductTitlesByWorseRating(double rating) {
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			if (product.getRating() <= rating) {
				logger.info("Product title: {}", product.getTitle());
			}
		}
	}

	@Override
	public Cart getCartWithHighestTotal() {
		List<Cart> carts = cartService.getAllCarts();
		Cart cartWithHighestTotal = carts.stream().max(Comparator.comparing(Cart::getTotal)).orElse(null);
		if (cartWithHighestTotal != null) {
			logger.info("Cart with the highest total: {}", cartWithHighestTotal);
		} else {
			logger.warn("No carts found");
		}
		return cartWithHighestTotal;
	}

	@Override
	public Cart getCartWithLowestTotal() {
		List<Cart> carts = cartService.getAllCarts();
		Cart cartWithLowestTotal = carts.stream().min(Comparator.comparing(Cart::getTotal)).orElse(null);
		if (cartWithLowestTotal != null) {
			logger.info("Cart with the lowest total: {}", cartWithLowestTotal);
		} else {
			logger.warn("No carts found");
		}
		return cartWithLowestTotal;
	}

	@Override
	public List<Product> addProductImagesToUserCart(Integer userId) {
		List<Cart> userCarts = cartService.getUserCarts(userId);
		if (!userCarts.isEmpty()) {
			Cart cart = userCarts.get(0);
			List<Product> cartProducts = cart.getProducts();
			for (Product product : cartProducts) {
				Integer productId = product.getId();
				Product enrichedProduct = productService.getProduct(productId);
				product.setImages(enrichedProduct.getImages());
				logger.info("Enriched product information: {}", product);
			}
			return cartProducts;
		}
		logger.warn("No user carts found for user ID: {}", userId);
		return null;
	}
}
