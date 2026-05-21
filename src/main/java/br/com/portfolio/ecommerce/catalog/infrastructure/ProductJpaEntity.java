package br.com.portfolio.ecommerce.catalog.infrastructure;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="products")
public class ProductJpaEntity {
 @Id public UUID id; public String sku; public String name; public String description; public BigDecimal price; public boolean active; public Instant createdAt; public Instant updatedAt;
}
