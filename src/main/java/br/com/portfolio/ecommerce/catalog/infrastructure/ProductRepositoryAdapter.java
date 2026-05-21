package br.com.portfolio.ecommerce.catalog.infrastructure;
import br.com.portfolio.ecommerce.catalog.domain.*; import br.com.portfolio.ecommerce.shared.domain.Money;
import org.springframework.data.domain.PageRequest; import org.springframework.stereotype.Repository; import java.util.*;
@Repository
public class ProductRepositoryAdapter implements ProductRepository {
 private final ProductJpaRepository jpa; public ProductRepositoryAdapter(ProductJpaRepository jpa){this.jpa=jpa;}
 public Product save(Product p){ var e=toEntity(p); return toDomain(jpa.save(e)); }
 public Optional<Product> findById(UUID id){ return jpa.findById(id).map(this::toDomain); }
 public Optional<Product> findBySku(String sku){ return jpa.findBySku(sku).map(this::toDomain); }
 public List<Product> search(String term,int page,int size){ return jpa.findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(term,term, PageRequest.of(page,size)).map(this::toDomain).toList(); }
 private Product toDomain(ProductJpaEntity e){ return new Product(e.id,e.sku,e.name,e.description, Money.of(e.price), e.active, e.createdAt, e.updatedAt); }
 private ProductJpaEntity toEntity(Product p){ var e=new ProductJpaEntity(); e.id=p.id(); e.sku=p.sku(); e.name=p.name(); e.description=p.description(); e.price=p.price().value(); e.active=p.active(); e.createdAt=p.createdAt(); e.updatedAt=p.updatedAt(); return e; }
}
