package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.Money; import org.junit.jupiter.api.Test; import java.math.BigDecimal; import java.util.*; import static org.junit.jupiter.api.Assertions.*;
class OrderTest {
 @Test void shouldCreateOrderWithTotalAmount(){ var item=OrderItem.create(UUID.randomUUID(),"SKU-1","Produto",2,Money.of(new BigDecimal("10.00"))); var order=Order.create(UUID.randomUUID(), List.of(item), "key-1"); assertEquals(new BigDecimal("20.00"), order.totalAmount().value()); assertEquals(OrderStatus.PAYMENT_PENDING, order.status()); assertEquals(1, order.pullEvents().size()); }
}
