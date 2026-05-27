package br.com.portfolio.ecommerce.catalog.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> { java.util.List<ProductJpaEntity> findByActiveTrue(); }
