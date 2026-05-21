package br.com.portfolio.ecommerce.outbox.infrastructure;
import org.springframework.amqp.rabbit.core.RabbitTemplate; import org.springframework.scheduling.annotation.Scheduled; import org.springframework.stereotype.Component; import org.springframework.transaction.annotation.Transactional; import java.time.Instant;
@Component
public class OutboxPublisherJob {
 private final OutboxJpaRepository repo; private final RabbitTemplate rabbit;
 public OutboxPublisherJob(OutboxJpaRepository repo, RabbitTemplate rabbit){this.repo=repo;this.rabbit=rabbit;}
 @Scheduled(fixedDelay = 10000)
 @Transactional
 public void publishPending(){
   for(var e: repo.findTop50ByStatusOrderByCreatedAtAsc("PENDING")){
     try { rabbit.convertAndSend("ecommerce.events", e.eventType, e.payload); e.status="PROCESSED"; e.processedAt= Instant.now(); }
     catch(Exception ex){ e.attempts++; if(e.attempts>=5) e.status="FAILED"; }
   }
 }
}
