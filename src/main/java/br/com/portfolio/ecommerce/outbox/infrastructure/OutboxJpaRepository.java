package br.com.portfolio.ecommerce.outbox.infrastructure;
import org.springframework.data.jpa.repository.*; import java.util.*;
public interface OutboxJpaRepository extends JpaRepository<OutboxJpaEntity, UUID> {
 List<OutboxJpaEntity> findTop50ByStatusOrderByCreatedAtAsc(String status);
}
