package br.com.portfolio.ecommerce.order.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, UUID> { Optional<OrderJpaEntity> findByIdempotencyKey(String idempotencyKey); }
