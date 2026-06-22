package com.mycompany.inventory.demo.repository;

import com.mycompany.inventory.demo.model.InventoryItem;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.Map;
import java.util.Optional;

@Repository
public class InventoryRepository {
    private final DynamoDbClient dynamoDbClient;
    private final String inventoryTableName;

    public InventoryRepository() {
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.of(System.getenv().getOrDefault("AWS_REGION",
                        System.getenv().getOrDefault("AWS_DEFAULT_REGION", "us-west-2"))))
                .build();
        this.inventoryTableName = System.getenv().getOrDefault("INVENTORY_TABLE_NAME", "demo-inventory-tf");
    }

    public Optional<InventoryItem> findByProductId(int productId) {
        Map<String, AttributeValue> item = dynamoDbClient.getItem(GetItemRequest.builder()
                        .tableName(inventoryTableName)
                        .key(Map.of("productId", AttributeValue.fromN(Integer.toString(productId))))
                        .build())
                .item();

        if (item == null || item.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(toInventoryItem(item));
    }

    private InventoryItem toInventoryItem(Map<String, AttributeValue> item) {
        int quantityAvailable = Integer.parseInt(item.get("quantityAvailable").n());
        return new InventoryItem(
                Integer.parseInt(item.get("productId").n()),
                quantityAvailable,
                item.get("warehouseRegion").s(),
                quantityAvailable > 0
        );
    }
}
