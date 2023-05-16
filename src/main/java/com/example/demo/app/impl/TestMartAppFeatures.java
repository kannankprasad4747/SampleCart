package com.example.demo.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class TestMartAppFeatures extends AbstractTestMartAppFeatures {

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
                System.out.println(product.getTitle());
            }
        }
    }

    @Override
    public Cart getCartWithHighestTotal() {
        List<Cart> carts = cartService.getAllCarts();
        return carts.stream()
                .max(Comparator.comparing(Cart::getTotal))
                .orElse(null);
    }

    @Override
    public Cart getCartWithLowestTotal() {
        List<Cart> carts = cartService.getAllCarts();
        return carts.stream()
                .min(Comparator.comparing(Cart::getTotal))
                .orElse(null);
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
            }
            return cartProducts;
        }
        return null;
    }
}
