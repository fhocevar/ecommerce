package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.*;
import java.time.Instant; import java.util.*;

public class Order {
    private final UUID id;
    private final UUID customerId;
    private OrderStatus status;
    private final List<OrderItem> items;
    private Money totalAmount;
    private final String idempotencyKey;
    private final Instant createdAt;
    private Instant updatedAt;
    private final List<DomainEvent> events = new ArrayList<>();

    public Order(UUID id, UUID customerId, OrderStatus status, List<OrderItem> items, Money totalAmount, String idempotencyKey, Instant createdAt, Instant updatedAt) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.customerId = Objects.requireNonNull(customerId);
        this.status = status == null ? OrderStatus.CREATED : status;
        this.items = new ArrayList<>(items == null ? List.of() : items);
        this.totalAmount = totalAmount == null ? calculateTotal(this.items) : totalAmount;
        this.idempotencyKey = idempotencyKey;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        this.updatedAt = updatedAt == null ? Instant.now() : updatedAt;
    }
    public static Order create(UUID customerId, List<OrderItem> items, String idempotencyKey){
        if(items == null || items.isEmpty()) throw new BusinessException("Order must contain at least one item");
        Order order = new Order(UUID.randomUUID(), customerId, OrderStatus.PAYMENT_PENDING, items, null, idempotencyKey, Instant.now(), Instant.now());
        order.events.add(OrderCreatedEvent.of(order.id)); return order;
    }
    private static Money calculateTotal(List<OrderItem> items){ return items.stream().map(OrderItem::totalPrice).reduce(Money.zero(), Money::add); }
    public void markPaid(){ if(status != OrderStatus.PAYMENT_PENDING) throw new BusinessException("Order is not awaiting payment"); status = OrderStatus.PAID; updatedAt = Instant.now(); }
    public UUID id(){return id;} public UUID customerId(){return customerId;} public OrderStatus status(){return status;} public List<OrderItem> items(){return List.copyOf(items);} public Money totalAmount(){return totalAmount;} public String idempotencyKey(){return idempotencyKey;} public Instant createdAt(){return createdAt;} public Instant updatedAt(){return updatedAt;} public List<DomainEvent> pullEvents(){ var copy=List.copyOf(events); events.clear(); return copy; }
}
