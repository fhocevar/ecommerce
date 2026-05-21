package br.com.portfolio.ecommerce.api;
import br.com.portfolio.ecommerce.catalog.domain.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/v1/products")
public class ProductController {
 private final ProductRepository repository; public ProductController(ProductRepository repository){this.repository=repository;}
 @GetMapping public List<ProductResponse> search(@RequestParam(defaultValue="") String q, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size){ return repository.search(q,page,size).stream().map(ProductResponse::from).toList(); }
 @GetMapping("/{id}") public ProductResponse get(@PathVariable UUID id){ return repository.findById(id).map(ProductResponse::from).orElseThrow(); }
 public record ProductResponse(UUID id,String sku,String name,String description,java.math.BigDecimal price,boolean active){ static ProductResponse from(Product p){ return new ProductResponse(p.id(),p.sku(),p.name(),p.description(),p.price().value(),p.active()); }}
}
