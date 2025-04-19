package org.vinio.domain;

import java.time.LocalDate;

public class Product {
    private final String id;
    private final String name;
    private int quantity;

    private final LocalDate expiryDate;
    private final TemperatureMode temperatureMode;

    private final int minimumStock;
    private final int optimalStock;
    private final int criticalLevel;

    public Product(String id, String name, int quantity, LocalDate expiryDate,
                   TemperatureMode temperatureMode, int minimumStock,
                   int optimalStock, int criticalLevel) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.temperatureMode = temperatureMode;
        this.minimumStock = minimumStock;
        this.optimalStock = optimalStock;
        this.criticalLevel = criticalLevel;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public boolean isBelowCritical() {
        return quantity <= criticalLevel;
    }

    public void decreaseQuantity(int used) {
        if (used <= quantity) {
            quantity -= used;
        } else {
            throw new IllegalArgumentException("Недостаточно запаса");
        }
    }

    public void increaseQuantity(int added) {
        this.quantity += added;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public TemperatureMode getTemperatureMode() { return temperatureMode; }
    public int getMinimumStock() { return minimumStock; }
    public int getOptimalStock() { return optimalStock; }
    public int getCriticalLevel() { return criticalLevel; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                ", temperatureMode=" + temperatureMode +
                ", minimumStock=" + minimumStock +
                ", optimalStock=" + optimalStock +
                ", criticalLevel=" + criticalLevel +
                '}';
    }
}