package br.com.portfolio.ecommerce.customer.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*; public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, UUID> {}
