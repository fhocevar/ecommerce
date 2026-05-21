package br.com.portfolio.ecommerce.inventory.infrastructure;
import br.com.portfolio.ecommerce.inventory.domain.*; import org.springframework.stereotype.Repository; import java.util.*;
@Repository
public class InventoryRepositoryAdapter implements InventoryRepository {
 private final InventoryJpaRepository jpa; public InventoryRepositoryAdapter(InventoryJpaRepository jpa){this.jpa=jpa;}
 public Optional<InventoryItem> findByProductIdForUpdate(UUID productId){ return jpa.findByProductIdForUpdate(productId).map(e -> new InventoryItem(e.productId,e.availableQuantity,e.reservedQuantity,e.version)); }
 public InventoryItem save(InventoryItem item){ var e=new InventoryJpaEntity(); e.productId=item.productId(); e.availableQuantity=item.availableQuantity(); e.reservedQuantity=item.reservedQuantity(); e.version=item.version(); var s=jpa.save(e); return new InventoryItem(s.productId,s.availableQuantity,s.reservedQuantity,s.version); }
}
