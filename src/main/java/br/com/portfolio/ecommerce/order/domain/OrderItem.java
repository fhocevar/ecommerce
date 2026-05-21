package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.Money;
import java.util.UUID;
public record OrderItem(UUID id, UUID productId, String sku, String productName, int quantity, Money unitPrice, Money totalPrice) {
    public static OrderItem create(UUID productId, String sku, String productName, int quantity, Money unitPrice) {
        return new OrderItem(UUID.randomUUID(), productId, sku, productName, quantity, unitPrice, unitPrice.multiply(quantity));
    }
}
