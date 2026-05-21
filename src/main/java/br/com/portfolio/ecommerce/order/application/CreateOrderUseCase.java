package br.com.portfolio.ecommerce.order.application;
import br.com.portfolio.ecommerce.catalog.domain.*;
import br.com.portfolio.ecommerce.inventory.domain.*;
import br.com.portfolio.ecommerce.order.domain.*;
import br.com.portfolio.ecommerce.outbox.application.OutboxService;
import br.com.portfolio.ecommerce.shared.domain.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CreateOrderUseCase {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;
    private final OutboxService outboxService;
    public CreateOrderUseCase(ProductRepository productRepository, InventoryRepository inventoryRepository, OrderRepository orderRepository, OutboxService outboxService) {
        this.productRepository = productRepository; this.inventoryRepository = inventoryRepository; this.orderRepository = orderRepository; this.outboxService = outboxService;
    }
    @Transactional
    public Order execute(CreateOrderCommand command) {
        orderRepository.findByIdempotencyKey(command.idempotencyKey()).ifPresent(existing -> { throw new DuplicateOrderException(existing); });
        List<OrderItem> orderItems = new ArrayList<>();
        for (var item : command.items()) {
            Product product = productRepository.findById(item.productId()).orElseThrow(() -> new BusinessException("Product not found: " + item.productId()));
            if (!product.active()) throw new BusinessException("Product inactive: " + product.sku());
            InventoryItem inventory = inventoryRepository.findByProductIdForUpdate(product.id()).orElseThrow(() -> new BusinessException("Inventory not found for product: " + product.id()));
            inventory.reserve(item.quantity());
            inventoryRepository.save(inventory);
            orderItems.add(OrderItem.create(product.id(), product.sku(), product.name(), item.quantity(), product.price()));
        }
        Order order = Order.create(command.customerId(), orderItems, command.idempotencyKey());
        Order saved = orderRepository.save(order);
        outboxService.store(saved.pullEvents());
        return saved;
    }
    public static class DuplicateOrderException extends RuntimeException { private final Order order; public DuplicateOrderException(Order order){ super("Duplicated idempotency key"); this.order=order;} public Order order(){return order;} }
}
