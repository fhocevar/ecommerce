package br.com.portfolio.ecommerce.order.application.port.in;
import java.util.*;
public record CheckoutCommand(UUID customerId, String couponCode, String shippingZipCode, java.util.List<Item> items, String idempotencyKey) { public record Item(UUID productId, int quantity){} }
