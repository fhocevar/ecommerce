package br.com.portfolio.ecommerce.inventory.application.port.out;
import br.com.portfolio.ecommerce.inventory.domain.InventoryItem; import java.util.*;
public interface InventoryRepository { Optional<InventoryItem> findByProductIdForUpdate(UUID productId); void save(InventoryItem item); }
