package org.vinio.infrastructure;

import org.vinio.domain.Inventory;
import org.vinio.domain.InventoryRepository;

public class InMemoryInventoryRepository implements InventoryRepository {
    private Inventory inventory = new Inventory();

    public Inventory load() {
        return inventory;
    }

    public void save(Inventory inventory) {
        this.inventory = inventory;
    }
}