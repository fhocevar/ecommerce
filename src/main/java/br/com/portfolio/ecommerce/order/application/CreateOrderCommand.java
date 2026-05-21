package br.com.portfolio.ecommerce.order.application;
import java.util.*;
public record CreateOrderCommand(UUID customerId, List<Item> items, String idempotencyKey) {
    public record Item(UUID productId, int quantity) {}
}
