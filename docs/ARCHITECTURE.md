# Arquitetura

O sistema segue Clean Architecture:

```text
API -> Application Use Cases -> Domain <- Infrastructure Adapters
```

A regra principal é: o domínio não depende de Spring, JPA, Redis ou RabbitMQ.

## Decisões enterprise

1. O carrinho fica no Redis porque é temporário e expira.
2. O pedido fica no PostgreSQL porque é transacional e auditável.
3. A reserva de estoque usa lock pessimista para evitar venda acima do estoque.
4. A criação de pedido usa `Idempotency-Key` para evitar duplicidade em retries.
5. Eventos de domínio são persistidos em `outbox_events` antes de serem publicados.
6. RabbitMQ é usado para integração assíncrona.
7. Flyway controla a evolução do banco.
