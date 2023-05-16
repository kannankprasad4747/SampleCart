package com.example.demo.model.controller;



import com.example.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService<Cart> cartService;

    public CartController(CartService<Cart> cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Integer cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getUserCarts(@PathVariable Integer userId) {
        return cartService.getUserCarts(userId);
    }
}
