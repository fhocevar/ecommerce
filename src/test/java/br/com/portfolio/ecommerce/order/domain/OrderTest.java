package br.com.portfolio.ecommerce.order.domain;
import br.com.portfolio.ecommerce.shared.domain.Money; import org.junit.jupiter.api.Test; import java.util.*; import static org.junit.jupiter.api.Assertions.*;
class OrderTest { @Test void shouldCalculateTotalWithDiscountAndShipping(){ var item=new OrderItem(UUID.randomUUID(),"Produto",2,Money.of("100.00")); var order=Order.checkout(UUID.randomUUID(),List.of(item),Money.of("20.00"),Money.of("10.00"),"k1"); assertEquals("190.00", order.total().amount().toString()); } }
