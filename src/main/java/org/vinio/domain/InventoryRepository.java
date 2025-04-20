package org.vinio.domain;


public interface InventoryRepository {
    Inventory load();

    void save(Inventory inventory);
}
