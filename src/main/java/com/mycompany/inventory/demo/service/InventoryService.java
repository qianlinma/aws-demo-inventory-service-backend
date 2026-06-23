package com.mycompany.inventory.demo.service;

import com.mycompany.inventory.demo.model.InventoryItem;

public interface InventoryService {
    InventoryItem getInventoryItem(int productId);
}
