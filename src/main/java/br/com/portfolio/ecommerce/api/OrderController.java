package br.com.portfolio.ecommerce.api;
import br.com.portfolio.ecommerce.order.application.*; import br.com.portfolio.ecommerce.order.domain.*; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/v1/orders")
public class OrderController {
 private final CreateOrderUseCase createOrder; private final OrderRepository orders;
 public OrderController(CreateOrderUseCase createOrder, OrderRepository orders){this.createOrder=createOrder;this.orders=orders;}
 @PostMapping public ResponseEntity<OrderResponse> create(@RequestHeader("Idempotency-Key") String key, @RequestBody @Valid CreateOrderRequest req){ try { return ResponseEntity.status(201).body(OrderResponse.from(createOrder.execute(req.toCommand(key)))); } catch(CreateOrderUseCase.DuplicateOrderException ex){ return ResponseEntity.ok(OrderResponse.from(ex.order())); } }
 @GetMapping("/{id}") public OrderResponse get(@PathVariable UUID id){ return orders.findById(id).map(OrderResponse::from).orElseThrow(); }
 public record CreateOrderRequest(@NotNull UUID customerId, @NotEmpty List<Item> items){ CreateOrderCommand toCommand(String key){ return new CreateOrderCommand(customerId, items.stream().map(i -> new CreateOrderCommand.Item(i.productId(), i.quantity())).toList(), key); } }
 public record Item(@NotNull UUID productId, @Min(1) int quantity){}
 public record OrderResponse(UUID id, UUID customerId, String status, java.math.BigDecimal totalAmount, List<OrderItemResponse> items){ static OrderResponse from(Order o){ return new OrderResponse(o.id(),o.customerId(),o.status().name(),o.totalAmount().value(),o.items().stream().map(i -> new OrderItemResponse(i.productId(),i.sku(),i.productName(),i.quantity(),i.unitPrice().value(),i.totalPrice().value())).toList()); }}
 public record OrderItemResponse(UUID productId,String sku,String productName,int quantity,java.math.BigDecimal unitPrice,java.math.BigDecimal totalPrice){}
}
