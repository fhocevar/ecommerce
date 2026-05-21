package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.DomainEvent;
import java.time.Instant; import java.util.UUID;
public record OrderCreatedEvent(UUID eventId, UUID aggregateId, Instant occurredAt, String eventType) implements DomainEvent {
    public static OrderCreatedEvent of(UUID orderId){ return new OrderCreatedEvent(UUID.randomUUID(), orderId, Instant.now(), "OrderCreated"); }
}
