package com.flipkart.backend.service;

import com.flipkart.backend.model.Cart;
import com.flipkart.backend.model.CartItem;
import com.flipkart.backend.model.Product;
import com.flipkart.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    
    private final CartRepository cartRepository;
    private final ProductService productService;
    
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    
    public Cart addItemToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        Product product = productService.getProductById(productId);
        
        // Check if product already exists in cart
        CartItem existingItem = cart.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .orElse(null);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }
        
        return cartRepository.save(cart);
    }
    
    public Cart updateCartItemQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = getCartByUserId(userId);
        
        CartItem cartItem = cart.getCartItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Item not found in cart"));
        
        if (quantity <= 0) {
            cart.getCartItems().remove(cartItem);
        } else {
            cartItem.setQuantity(quantity);
        }
        
        return cartRepository.save(cart);
    }
    
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        
        cart.getCartItems().removeIf(item -> 
            item.getProduct().getId().equals(productId));
        
        return cartRepository.save(cart);
    }
    
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}
