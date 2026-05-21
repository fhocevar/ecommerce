package br.com.portfolio.ecommerce.shared.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal value) {
    public Money {
        if (value == null) throw new IllegalArgumentException("Money value is required");
        value = value.setScale(2, RoundingMode.HALF_UP);
        if (value.signum() < 0) throw new IllegalArgumentException("Money cannot be negative");
    }
    public static Money of(BigDecimal value) { return new Money(value); }
    public static Money zero() { return new Money(BigDecimal.ZERO); }
    public Money multiply(int quantity) { return new Money(value.multiply(BigDecimal.valueOf(quantity))); }
    public Money add(Money other) { return new Money(value.add(other.value)); }
}
