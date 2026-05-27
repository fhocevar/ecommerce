package br.com.portfolio.ecommerce.catalog.infrastructure.persistence;
import jakarta.persistence.*; import java.math.BigDecimal; import java.util.*;
@Entity @Table(name="products") public class ProductJpaEntity { @Id private UUID id; private String sku; private String name; private BigDecimal price; private boolean active; protected ProductJpaEntity(){} public UUID getId(){return id;} public String getSku(){return sku;} public String getName(){return name;} public BigDecimal getPrice(){return price;} public boolean isActive(){return active;} }
