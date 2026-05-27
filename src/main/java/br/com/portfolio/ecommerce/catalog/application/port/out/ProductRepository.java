package br.com.portfolio.ecommerce.catalog.application.port.out;
import br.com.portfolio.ecommerce.catalog.domain.Product; import java.util.*;
public interface ProductRepository { Optional<Product> findById(UUID id); java.util.List<Product> findActive(); }
