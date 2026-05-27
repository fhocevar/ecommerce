package br.com.portfolio.ecommerce.order.application.port.out;
import br.com.portfolio.ecommerce.order.domain.Order; import java.util.*;
public interface OrderRepository { Optional<Order> findByIdempotencyKey(String key); Optional<Order> findById(UUID id); Order save(Order order); }
