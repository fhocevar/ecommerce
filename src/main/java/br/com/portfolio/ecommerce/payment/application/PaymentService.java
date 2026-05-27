package br.com.portfolio.ecommerce.payment.application;

import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void approve(UUID orderId, String externalReference) {
        System.out.println(
                "Payment approved for order %s with reference %s"
                        .formatted(orderId, externalReference)
        );
    }
}