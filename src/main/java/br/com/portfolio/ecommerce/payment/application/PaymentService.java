package br.com.portfolio.ecommerce.payment.application;
import br.com.portfolio.ecommerce.order.domain.*;
import br.com.portfolio.ecommerce.payment.infrastructure.PaymentJpaEntity;
import br.com.portfolio.ecommerce.payment.infrastructure.PaymentJpaRepository;
import br.com.portfolio.ecommerce.shared.domain.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class PaymentService {
    private final OrderRepository orderRepository; private final PaymentJpaRepository paymentRepository;
    public PaymentService(OrderRepository orderRepository, PaymentJpaRepository paymentRepository){this.orderRepository=orderRepository;this.paymentRepository=paymentRepository;}
    @Transactional
    public void approvePayment(UUID orderId, String externalReference){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        order.markPaid(); orderRepository.save(order);
        paymentRepository.save(PaymentJpaEntity.approved(order.id(), order.totalAmount().value(), externalReference));
    }
}
