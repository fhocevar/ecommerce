# E-commerce Enterprise Senior V2

Projeto Java para portfólio senior com DDD, Clean Architecture, SOLID, Spring Boot, PostgreSQL, Redis, RabbitMQ, Outbox Pattern, JWT, auditoria e testes.

## Destaques arquiteturais

- Bounded contexts: identity, customer, catalog, inventory, cart, order, payment, shipping, promotion, notification, audit e outbox.
- Camada application com `port/in`, `port/out` e `usecase`.
- Infra separada em persistence, messaging, security e cache.
- Redis para carrinho.
- RabbitMQ para integração assíncrona.
- Outbox Pattern para publicação confiável.
- Idempotency-Key no checkout.
- Pessimistic Lock em estoque.
- Flyway para schema e seed.
- Actuator + Prometheus.
- GitHub Actions.

## Subir local

```bash
docker compose up --build
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

RabbitMQ:

```text
http://localhost:15672
user: ecommerce
pass: ecommerce
```

## Fluxo rápido

```bash
curl -X POST http://localhost:8080/api/v1/auth/login   -H "Content-Type: application/json"   -d '{"email":"admin@portfolio.com","password":"admin123"}'
```

```bash
curl http://localhost:8080/api/v1/products
```

```bash
curl -X POST http://localhost:8080/api/v1/orders/checkout   -H "Content-Type: application/json"   -H "Idempotency-Key: pedido-portfolio-001"   -d '{
    "customerId":"11111111-1111-1111-1111-111111111111",
    "couponCode":"WELCOME10",
    "shippingZipCode":"01001000",
    "items":[
      {"productId":"22222222-2222-2222-2222-222222222221","quantity":1},
      {"productId":"22222222-2222-2222-2222-222222222222","quantity":2}
    ]
  }'
```

## Estrutura

```text
identity/api, application, domain, infrastructure/security
customer/api, application, domain, infrastructure/persistence
catalog/api, application, domain, infrastructure/persistence
inventory/application, domain, infrastructure/persistence
cart/api, application, infrastructure/cache
order/api, application/port/in, application/port/out, application/usecase, domain, infrastructure/persistence
payment/api, application, domain, infrastructure/persistence, infrastructure/messaging
shipping/application, domain
promotion/application, domain
notification/application, infrastructure/messaging
audit/application, infrastructure/persistence
outbox/application, infrastructure/persistence, infrastructure/messaging
shared/domain, shared/api, shared/config
```
