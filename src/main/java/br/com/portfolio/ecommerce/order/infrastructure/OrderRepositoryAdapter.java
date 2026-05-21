package br.com.portfolio.ecommerce.order.infrastructure;
import br.com.portfolio.ecommerce.order.domain.*; import br.com.portfolio.ecommerce.shared.domain.Money; import org.springframework.stereotype.Repository; import java.util.*;
@Repository
public class OrderRepositoryAdapter implements OrderRepository {
 private final OrderJpaRepository jpa; public OrderRepositoryAdapter(OrderJpaRepository jpa){this.jpa=jpa;}
 public Order save(Order order){ return toDomain(jpa.save(toEntity(order))); }
 public Optional<Order> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
 public Optional<Order> findByIdempotencyKey(String key){ return jpa.findByIdempotencyKey(key).map(this::toDomain); }
 private Order toDomain(OrderJpaEntity e){ var items=e.items.stream().map(i -> new OrderItem(i.id,i.productId,i.sku,i.productName,i.quantity, Money.of(i.unitPrice), Money.of(i.totalPrice))).toList(); return new Order(e.id,e.customerId,OrderStatus.valueOf(e.status),items,Money.of(e.totalAmount),e.idempotencyKey,e.createdAt,e.updatedAt); }
 private OrderJpaEntity toEntity(Order o){ var e=new OrderJpaEntity(); e.id=o.id(); e.customerId=o.customerId(); e.status=o.status().name(); e.totalAmount=o.totalAmount().value(); e.idempotencyKey=o.idempotencyKey(); e.createdAt=o.createdAt(); e.updatedAt=o.updatedAt(); for(var item:o.items()){ var i=new OrderItemJpaEntity(); i.id=item.id(); i.order=e; i.productId=item.productId(); i.sku=item.sku(); i.productName=item.productName(); i.quantity=item.quantity(); i.unitPrice=item.unitPrice().value(); i.totalPrice=item.totalPrice().value(); e.items.add(i);} return e; }
}
