package br.com.portfolio.ecommerce.inventory.domain;
import br.com.portfolio.ecommerce.shared.domain.BusinessException;
import java.util.UUID;

public class InventoryItem {
    private final UUID productId;
    private int availableQuantity;
    private int reservedQuantity;
    private long version;
    public InventoryItem(UUID productId, int availableQuantity, int reservedQuantity, long version) {
        this.productId = productId; this.availableQuantity = availableQuantity; this.reservedQuantity = reservedQuantity; this.version = version;
    }
    public UUID productId(){ return productId; }
    public int availableQuantity(){ return availableQuantity; }
    public int reservedQuantity(){ return reservedQuantity; }
    public long version(){ return version; }
    public void reserve(int quantity){
        if(quantity <= 0) throw new BusinessException("Quantity must be positive");
        if(availableQuantity < quantity) throw new BusinessException("Insufficient inventory for product " + productId);
        availableQuantity -= quantity; reservedQuantity += quantity;
    }
    public void commitReservation(int quantity){
        if(reservedQuantity < quantity) throw new BusinessException("Reserved inventory is insufficient");
        reservedQuantity -= quantity;
    }
    public void release(int quantity){ reservedQuantity -= quantity; availableQuantity += quantity; }
}
