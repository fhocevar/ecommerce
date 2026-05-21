package br.com.portfolio.ecommerce.catalog.infrastructure;
import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> {
 Optional<ProductJpaEntity> findBySku(String sku);
 Page<ProductJpaEntity> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(String name, String sku, Pageable pageable);
}
