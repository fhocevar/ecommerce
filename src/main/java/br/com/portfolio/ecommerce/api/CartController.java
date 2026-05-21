package br.com.portfolio.ecommerce.api;
import br.com.portfolio.ecommerce.cart.application.CartService; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/v1/customers/{customerId}/cart")
public class CartController {
 private final CartService service; public CartController(CartService service){this.service=service;}
 @PostMapping("/items") public void add(@PathVariable UUID customerId, @RequestBody @Valid AddItemRequest req){ service.addItem(customerId, req.productId(), req.quantity()); }
 @GetMapping public Map<Object,Object> get(@PathVariable UUID customerId){ return service.getCart(customerId); }
 @DeleteMapping public void clear(@PathVariable UUID customerId){ service.clear(customerId); }
 public record AddItemRequest(@NotNull UUID productId, @Min(1) int quantity){}
}
