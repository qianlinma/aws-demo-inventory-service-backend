package com.mycompany.inventory.demo.model;

public record InventoryItem(
        int productId,
        int quantityAvailable,
        String warehouseRegion,
        boolean available
) {
}
