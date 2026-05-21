package br.com.portfolio.ecommerce.catalog.domain;
import java.util.*;
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    Optional<Product> findBySku(String sku);
    List<Product> search(String term, int page, int size);
}
