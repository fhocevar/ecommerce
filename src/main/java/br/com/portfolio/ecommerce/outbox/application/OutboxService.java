package br.com.portfolio.ecommerce.outbox.application;

import br.com.portfolio.ecommerce.outbox.infrastructure.persistence.OutboxJpaEntity;
import br.com.portfolio.ecommerce.outbox.infrastructure.persistence.OutboxJpaRepository;
import br.com.portfolio.ecommerce.shared.domain.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    private final OutboxJpaRepository repo;
    private final ObjectMapper objectMapper;

    public OutboxService(
            OutboxJpaRepository repo,
            ObjectMapper objectMapper
    ) {
        this.repo = repo;
        this.objectMapper = objectMapper;
    }

    public void store(DomainEvent event) {
        repo.save(new OutboxJpaEntity(
                event.eventId(),
                event.eventType(),
                event.occurredAt(),
                toJson(event),
                false,
                Instant.now()
        ));
    }

    private String toJson(DomainEvent event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Erro ao serializar evento de domínio", e);
        }
    }
}