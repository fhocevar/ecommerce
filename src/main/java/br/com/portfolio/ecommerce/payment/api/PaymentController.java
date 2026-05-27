package br.com.portfolio.ecommerce.payment.api;
import br.com.portfolio.ecommerce.payment.application.PaymentService; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/v1/payments") public class PaymentController { private final PaymentService service; public PaymentController(PaymentService service){this.service=service;} @PostMapping("/orders/{orderId}/approve") void approve(@PathVariable UUID orderId,@RequestParam String externalReference){ service.approve(orderId,externalReference); } }
