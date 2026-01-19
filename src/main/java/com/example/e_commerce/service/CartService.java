package com.example.e_commerce.service;

import com.example.e_commerce.model.CartItem;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CartRepository;
import com.example.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    // Add product to cart
    public CartItem addToCart(String userId, String productId, int quantity) {

        // Validate product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check existing cart item
        CartItem cartItem = cartRepository
                .findByUserIdAndProductId(userId, productId)
                .orElse(new CartItem());

        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        return cartRepository.save(cartItem);
    }

    // Get cart items by user
    public List<CartItem> getCart(String userId) {
        return cartRepository.findByUserId(userId);
    }

    // Clear cart
    public void clearCart(String userId) {
        cartRepository.deleteByUserId(userId);
    }
}
