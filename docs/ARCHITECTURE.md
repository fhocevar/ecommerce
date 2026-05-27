# Architecture

Este projeto demonstra uma arquitetura de e-commerce orientada a domínio.

## Decisões

- O domínio não depende de Spring.
- Use cases orquestram transações e integrações.
- Repositories são portas de saída.
- Adapters JPA implementam as portas.
- Outbox grava eventos no mesmo banco da transação de negócio.
- Um job publica eventos pendentes no RabbitMQ.
- Consumers tratam eventos de pagamento e notificação.
- Redis é usado para carrinho temporário.
- Idempotência impede duplicidade de checkout.

## Fluxo de checkout

1. API recebe `CheckoutCommand` com `Idempotency-Key`.
2. Use case valida cliente, cupom, produtos e estoque.
3. Reserva estoque com lock pessimista.
4. Calcula frete e desconto.
5. Cria pedido com status `PENDING_PAYMENT`.
6. Persiste evento em outbox.
7. Job publica no RabbitMQ.
8. Serviço de pagamento simulado aprova ou rejeita.
9. Consumer atualiza status do pedido.
