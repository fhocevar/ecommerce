package br.com.portfolio.ecommerce.promotion.application;
import br.com.portfolio.ecommerce.promotion.domain.Coupon; import br.com.portfolio.ecommerce.shared.domain.Money; import org.springframework.stereotype.Service; import java.math.BigDecimal;
@Service public class PromotionService { public Money calculateDiscount(String code, Money subtotal){ if(code==null || code.isBlank()) return Money.zero(); if("WELCOME10".equalsIgnoreCase(code)) return new Coupon(code, BigDecimal.TEN, true).discount(subtotal); return Money.zero(); } }
