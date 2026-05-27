package br.com.portfolio.ecommerce.outbox.application;
import br.com.portfolio.ecommerce.outbox.infrastructure.persistence.*; import br.com.portfolio.ecommerce.shared.domain.DomainEvent; import org.springframework.stereotype.Service; import java.time.*;
@Service public class OutboxService { private final OutboxJpaRepository repo; public OutboxService(OutboxJpaRepository repo){this.repo=repo;} public void store(DomainEvent event){ repo.save(new OutboxJpaEntity(event.eventId(),event.eventType(),event.occurredAt(),"{}",false,Instant.now())); } }
