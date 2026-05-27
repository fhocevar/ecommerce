package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.DomainEvent; import java.time.*; import java.util.*;
public record OrderCreatedEvent(UUID eventId, UUID orderId, UUID customerId, String eventType, Instant occurredAt) implements DomainEvent { public static OrderCreatedEvent of(Order order){ return new OrderCreatedEvent(UUID.randomUUID(), order.id(), order.customerId(), "order.created", Instant.now()); } }
