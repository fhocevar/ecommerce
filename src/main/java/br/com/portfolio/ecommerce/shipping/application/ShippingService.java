package br.com.portfolio.ecommerce.shipping.application;
import br.com.portfolio.ecommerce.shared.domain.Money; import br.com.portfolio.ecommerce.shipping.domain.ShippingQuote; import org.springframework.stereotype.Service;
@Service public class ShippingService { public ShippingQuote quote(String zipCode){ if(zipCode!=null && zipCode.startsWith("01")) return new ShippingQuote(zipCode, Money.of("15.90"), 2); return new ShippingQuote(zipCode, Money.of("29.90"), 5); } }
