package com.mycompany.inventory.demo.service;

import com.mycompany.inventory.demo.model.InventoryItem;
import com.mycompany.inventory.demo.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryItem getInventoryItem(int productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseGet(() -> new InventoryItem(productId, 0, "unknown", false));
    }
}
