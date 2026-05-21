package br.com.portfolio.ecommerce.inventory.domain;
import java.util.*;
public interface InventoryRepository {
    Optional<InventoryItem> findByProductIdForUpdate(UUID productId);
    InventoryItem save(InventoryItem item);
}
