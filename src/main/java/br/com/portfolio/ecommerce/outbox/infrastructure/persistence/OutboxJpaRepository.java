package br.com.portfolio.ecommerce.outbox.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface OutboxJpaRepository extends JpaRepository<OutboxJpaEntity, UUID> { java.util.List<OutboxJpaEntity> findTop50ByPublishedFalseOrderByCreatedAtAsc(); }
