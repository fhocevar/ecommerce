package br.com.portfolio.ecommerce.inventory.infrastructure;
import jakarta.persistence.LockModeType; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface InventoryJpaRepository extends JpaRepository<InventoryJpaEntity, UUID> {
 @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select i from InventoryJpaEntity i where i.productId = :productId") Optional<InventoryJpaEntity> findByProductIdForUpdate(@Param("productId") UUID productId);
}
