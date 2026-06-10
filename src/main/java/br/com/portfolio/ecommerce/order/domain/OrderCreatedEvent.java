package br.com.portfolio.ecommerce.order.domain;

import br.com.portfolio.ecommerce.shared.domain.DomainEvent;
import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID eventId,
        UUID orderId,
        UUID customerId,
        String status,
        Instant occurredAt
) implements DomainEvent {

    public static OrderCreatedEvent of(Order order) {
        return new OrderCreatedEvent(
                UUID.randomUUID(),
                order.id(),
                order.customerId(),
                order.status().name(),
                Instant.now()
        );
    }

    @Override
    public String eventType() {
        return "order.created";
    }
}