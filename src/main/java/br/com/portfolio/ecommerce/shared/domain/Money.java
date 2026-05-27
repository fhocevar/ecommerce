package br.com.portfolio.ecommerce.shared.domain;
import java.math.BigDecimal; import java.math.RoundingMode;
public record Money(BigDecimal amount) {
    public Money { if (amount == null || amount.signum() < 0) throw new BusinessException("Valor monetário inválido"); amount = amount.setScale(2, RoundingMode.HALF_UP); }
    public static Money zero(){ return new Money(BigDecimal.ZERO); }
    public static Money of(String v){ return new Money(new BigDecimal(v)); }
    public Money add(Money other){ return new Money(amount.add(other.amount)); }
    public Money subtract(Money other){ return new Money(amount.subtract(other.amount).max(BigDecimal.ZERO)); }
    public Money multiply(int q){ return new Money(amount.multiply(BigDecimal.valueOf(q))); }
}
