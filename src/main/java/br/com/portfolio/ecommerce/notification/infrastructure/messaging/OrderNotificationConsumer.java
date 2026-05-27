package br.com.portfolio.ecommerce.notification.infrastructure.messaging;
import br.com.portfolio.ecommerce.notification.application.NotificationService; import org.springframework.amqp.rabbit.annotation.RabbitListener; import org.springframework.stereotype.Component;
@Component public class OrderNotificationConsumer { private final NotificationService service; public OrderNotificationConsumer(NotificationService service){this.service=service;} @RabbitListener(queues="ecommerce.order-created") public void orderCreated(String payload){ service.notifyCustomer("Pedido criado: "+payload); } }
