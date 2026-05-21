package br.com.portfolio.ecommerce.outbox.infrastructure;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="outbox_events")
public class OutboxJpaEntity { @Id public UUID id; public String aggregateType; public UUID aggregateId; public String eventType; @Column(columnDefinition="jsonb") public String payload; public String status; public int attempts; public Instant createdAt; public Instant processedAt; }
