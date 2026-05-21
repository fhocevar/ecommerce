package br.com.portfolio.ecommerce.catalog.domain;
import br.com.portfolio.ecommerce.shared.domain.Money;
import java.time.Instant;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String sku;
    private String name;
    private String description;
    private Money price;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;

    public Product(UUID id, String sku, String name, String description, Money price, boolean active, Instant createdAt, Instant updatedAt) {
        if (sku == null || sku.isBlank()) throw new IllegalArgumentException("SKU is required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name is required");
        this.id = id == null ? UUID.randomUUID() : id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt == null ? Instant.now() : createdAt;
        this.updatedAt = updatedAt == null ? Instant.now() : updatedAt;
    }
    public UUID id(){return id;} public String sku(){return sku;} public String name(){return name;} public String description(){return description;} public Money price(){return price;} public boolean active(){return active;}
    public Instant createdAt(){return createdAt;} public Instant updatedAt(){return updatedAt;}
    public void changePrice(Money newPrice){ this.price = newPrice; this.updatedAt = Instant.now(); }
    public void deactivate(){ this.active = false; this.updatedAt = Instant.now(); }
}
