package br.com.portfolio.ecommerce.promotion.domain;
import br.com.portfolio.ecommerce.shared.domain.Money; import java.math.BigDecimal;
public class Coupon { private final String code; private final BigDecimal percent; private final boolean active; public Coupon(String code,BigDecimal percent,boolean active){this.code=code;this.percent=percent;this.active=active;} public Money discount(Money subtotal){ if(!active) return Money.zero(); return new Money(subtotal.amount().multiply(percent).divide(BigDecimal.valueOf(100))); } public String code(){return code;} }
