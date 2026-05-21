package br.com.portfolio.ecommerce.order.infrastructure;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant; import java.util.*;
@Entity @Table(name="orders")
public class OrderJpaEntity {
 @Id public UUID id; public UUID customerId; public String status; public BigDecimal totalAmount; public String idempotencyKey; public Instant createdAt; public Instant updatedAt;
 @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER) public List<OrderItemJpaEntity> items = new ArrayList<>();
}
