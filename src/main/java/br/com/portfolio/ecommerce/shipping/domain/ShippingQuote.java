package br.com.portfolio.ecommerce.shipping.domain;
import br.com.portfolio.ecommerce.shared.domain.Money;
public record ShippingQuote(String zipCode, Money price, int estimatedDays) {}
