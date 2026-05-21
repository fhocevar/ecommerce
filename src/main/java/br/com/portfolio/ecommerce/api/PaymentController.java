package br.com.portfolio.ecommerce.api;
import br.com.portfolio.ecommerce.payment.application.PaymentService; import org.springframework.web.bind.annotation.*; import java.util.UUID;
@RestController @RequestMapping("/api/v1/payments")
public class PaymentController {
 private final PaymentService service; public PaymentController(PaymentService service){this.service=service;}
 @PostMapping("/orders/{orderId}/approve") public void approve(@PathVariable UUID orderId, @RequestParam(defaultValue="SIM-123") String externalReference){ service.approvePayment(orderId, externalReference); }
}
