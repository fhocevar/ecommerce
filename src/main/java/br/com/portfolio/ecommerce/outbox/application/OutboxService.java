package br.com.portfolio.ecommerce.outbox.application;
import br.com.portfolio.ecommerce.outbox.infrastructure.*; import br.com.portfolio.ecommerce.shared.domain.DomainEvent; import org.springframework.stereotype.Service; import java.time.Instant; import java.util.*;
@Service
public class OutboxService {
 private final OutboxJpaRepository repo; public OutboxService(OutboxJpaRepository repo){this.repo=repo;}
 public void store(List<DomainEvent> events){ for(var ev: events){ var e=new OutboxJpaEntity(); e.id=ev.eventId(); e.aggregateType="Order"; e.aggregateId=ev.aggregateId(); e.eventType=ev.eventType(); e.payload="{\"aggregateId\":\""+ev.aggregateId()+"\",\"eventType\":\""+ev.eventType()+"\"}"; e.status="PENDING"; e.createdAt= Instant.now(); repo.save(e);} }
}
