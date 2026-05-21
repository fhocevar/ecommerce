package br.com.portfolio.ecommerce.inventory.infrastructure;
import jakarta.persistence.*; import java.util.UUID;
@Entity @Table(name="inventory_items")
public class InventoryJpaEntity { @Id public UUID productId; public int availableQuantity; public int reservedQuantity; @Version public long version; }
