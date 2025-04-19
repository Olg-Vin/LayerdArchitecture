package org.vinio.infrastructure;

import org.vinio.domain.Inventory;

public interface InventoryRepository {
    Inventory load();
    void save(Inventory inventory);
}
