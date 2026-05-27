package br.com.portfolio.ecommerce.inventory.domain;
import br.com.portfolio.ecommerce.shared.domain.BusinessException; import org.junit.jupiter.api.Test; import java.util.*; import static org.junit.jupiter.api.Assertions.*;
class InventoryItemTest { @Test void shouldReserveStock(){ var item=new InventoryItem(UUID.randomUUID(),5); item.reserve(2); assertEquals(3,item.available()); } @Test void shouldRejectWhenInsufficient(){ var item=new InventoryItem(UUID.randomUUID(),1); assertThrows(BusinessException.class,()->item.reserve(2)); } }
