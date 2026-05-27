package br.com.portfolio.ecommerce.order.infrastructure.persistence;
import jakarta.persistence.*; import java.math.BigDecimal; import java.util.*;
@Entity @Table(name="order_items") public class OrderItemJpaEntity { @Id @GeneratedValue Long id; UUID productId; String productName; int quantity; BigDecimal unitPrice; @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id") OrderJpaEntity order; protected OrderItemJpaEntity(){} }
