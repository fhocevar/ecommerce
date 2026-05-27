package br.com.portfolio.ecommerce.identity.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserAccountJpaRepository extends JpaRepository<UserAccountJpaEntity, UUID> { Optional<UserAccountJpaEntity> findByEmail(String email); }
