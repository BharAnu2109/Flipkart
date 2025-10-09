package com.flipkart.backend.controller;

import com.flipkart.backend.model.Cart;
import com.flipkart.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {
    
    private final CartService cartService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }
    
    @PostMapping("/user/{userId}/items")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(
            cartService.addItemToCart(userId, productId, quantity));
    }
    
    @PutMapping("/user/{userId}/items/{productId}")
    public ResponseEntity<Cart> updateCartItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(
            cartService.updateCartItemQuantity(userId, productId, quantity));
    }
    
    @DeleteMapping("/user/{userId}/items/{productId}")
    public ResponseEntity<Cart> removeItemFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(
            cartService.removeItemFromCart(userId, productId));
    }
    
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
