package br.com.portfolio.ecommerce.order.infrastructure;
import jakarta.persistence.*; import java.math.BigDecimal; import java.util.UUID;
@Entity @Table(name="order_items")
public class OrderItemJpaEntity {
 @Id public UUID id; @ManyToOne @JoinColumn(name="order_id") public OrderJpaEntity order; public UUID productId; public String sku; public String productName; public int quantity; public BigDecimal unitPrice; public BigDecimal totalPrice;
}
