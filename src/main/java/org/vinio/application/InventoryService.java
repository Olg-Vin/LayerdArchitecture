package org.vinio.application;

import org.vinio.domain.Inventory;
import org.vinio.domain.InventoryRepository;
import org.vinio.domain.Product;

import java.util.List;


public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void addProduct(Product product) {
        Inventory inventory = repository.load();
        inventory.addProduct(product);
        repository.save(inventory);
    }

    public void adjustQuantity(String productId, int quantity) {
        Inventory inventory = repository.load();
        inventory.adjustQuantity(productId, quantity);
        repository.save(inventory);
    }

    public void useProduct(String productId, int quantity) {
        Inventory inventory = repository.load();
        inventory.useProduct(productId, quantity);
        repository.save(inventory);
    }

    public void writeOffExpired() {
        Inventory inventory = repository.load();
        inventory.writeOffExpired();
        repository.save(inventory);
    }

    public List<Product> getCriticalProducts() {
        Inventory inventory = repository.load();
        return inventory.getProductsBelowCriticalLevel();
    }

    public List<Product> getAllProducts() {
        return repository.load().getAllProducts();
    }
}