package br.com.portfolio.ecommerce.shared.infrastructure;
import org.springframework.amqp.core.*; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitConfig {
 @Bean TopicExchange ecommerceExchange(){ return new TopicExchange("ecommerce.events"); }
 @Bean Queue orderCreatedQueue(){ return new Queue("order.created.queue", true); }
 @Bean Binding orderCreatedBinding(){ return BindingBuilder.bind(orderCreatedQueue()).to(ecommerceExchange()).with("OrderCreated"); }
}
