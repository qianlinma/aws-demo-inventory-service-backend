package com.mycompany.inventory.demo.controller;

import com.mycompany.inventory.demo.model.InventoryItem;
import com.mycompany.inventory.demo.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public InventoryItem getInventory(@PathVariable int productId) {
        return inventoryService.getInventoryItem(productId);
    }
}
