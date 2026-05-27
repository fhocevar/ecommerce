package br.com.portfolio.ecommerce.catalog.application.usecase;
import br.com.portfolio.ecommerce.catalog.application.port.out.ProductRepository; import br.com.portfolio.ecommerce.catalog.domain.Product; import org.springframework.stereotype.Service; import java.util.*;
@Service public class ListProductsUseCase { private final ProductRepository products; public ListProductsUseCase(ProductRepository products){this.products=products;} public List<Product> execute(){ return products.findActive(); } }
