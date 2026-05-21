package br.com.portfolio.ecommerce.payment.infrastructure;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="payments")
public class PaymentJpaEntity {
 @Id public UUID id; public UUID orderId; public String status; public String provider; public String externalReference; public BigDecimal amount; public Instant createdAt;
 public static PaymentJpaEntity approved(UUID orderId, BigDecimal amount, String ext){ var p=new PaymentJpaEntity(); p.id=UUID.randomUUID(); p.orderId=orderId; p.status="APPROVED"; p.provider="SIMULATED_PROVIDER"; p.externalReference=ext; p.amount=amount; p.createdAt=Instant.now(); return p; }
}
