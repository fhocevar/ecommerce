package br.com.portfolio.ecommerce.order.application.port.in;
import br.com.portfolio.ecommerce.order.domain.Order; public interface CheckoutUseCase { Order checkout(CheckoutCommand command); }
