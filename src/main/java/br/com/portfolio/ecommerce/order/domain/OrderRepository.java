package br.com.portfolio.ecommerce.order.domain;
import java.util.*;
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID id);
    Optional<Order> findByIdempotencyKey(String idempotencyKey);
}
