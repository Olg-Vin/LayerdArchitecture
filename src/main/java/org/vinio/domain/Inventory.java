package org.vinio.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void adjustQuantity(String productId, int quantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.increaseQuantity(quantity);
        }
    }

    public void useProduct(String productId, int quantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.decreaseQuantity(quantity);
        }
    }

    public void writeOffExpired() {
        products.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Product> getProductsBelowCriticalLevel() {
        return products.values().stream()
                .filter(Product::isBelowCritical)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                '}';
    }
}