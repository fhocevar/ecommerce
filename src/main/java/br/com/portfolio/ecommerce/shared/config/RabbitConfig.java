package br.com.portfolio.ecommerce.shared.config;
import org.springframework.amqp.core.*; import org.springframework.beans.factory.annotation.Value; import org.springframework.context.annotation.*;
@Configuration
public class RabbitConfig {
 @Bean TopicExchange ecommerceExchange(@Value("${app.messaging.exchange}") String exchange){ return new TopicExchange(exchange, true, false); }
 @Bean Queue orderCreatedQueue(){ return QueueBuilder.durable("ecommerce.order-created").build(); }
 @Bean Queue paymentApprovedQueue(){ return QueueBuilder.durable("ecommerce.payment-approved").build(); }
 @Bean Binding orderCreatedBinding(Queue orderCreatedQueue, TopicExchange ecommerceExchange){ return BindingBuilder.bind(orderCreatedQueue).to(ecommerceExchange).with("order.created"); }
 @Bean Binding paymentApprovedBinding(Queue paymentApprovedQueue, TopicExchange ecommerceExchange){ return BindingBuilder.bind(paymentApprovedQueue).to(ecommerceExchange).with("payment.approved"); }
}
