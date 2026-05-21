# E-commerce Enterprise Senior — Java + DDD + Clean Architecture + Spring Boot

Projeto de portfólio com foco em arquitetura enterprise, separando domínio, aplicação, infraestrutura e API.

## Stack

- Java 21
- Spring Boot 3
- PostgreSQL 16
- Redis 7
- RabbitMQ
- Flyway
- JPA/Hibernate
- Actuator + Prometheus
- Docker Compose
- JUnit 5
- Testcontainers preparado no `pom.xml`

## Bounded Contexts

```text
catalog      -> produtos, preço e disponibilidade comercial
inventory    -> reserva e baixa de estoque
cart         -> carrinho temporário em Redis
order        -> criação de pedidos, status e idempotência
payment      -> pagamento simulado
outbox       -> publicação confiável de eventos
shared       -> kernel compartilhado, erros, money, eventos
api          -> controllers REST
```

## Padrões implementados

- DDD com entidades ricas
- Repositories como portas do domínio
- Adapters JPA na infraestrutura
- Use Cases na camada application
- Idempotency-Key no checkout
- Pessimistic Lock no estoque
- Outbox Pattern para eventos
- Redis para carrinho
- Flyway para versionamento de banco
- Tratamento global de erros
- Health check e Prometheus
- Docker Compose com Postgres, Redis, RabbitMQ e API

## Como subir

```bash
docker compose up --build
```

API:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

RabbitMQ Management:

```text
http://localhost:15672
usuario: ecommerce
senha: ecommerce
```

## Fluxo de teste

### Listar produtos

```bash
curl http://localhost:8080/api/v1/products
```

### Criar pedido

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "Idempotency-Key: pedido-portfolio-001" \
  -d '{
    "customerId": "11111111-1111-1111-1111-111111111111",
    "items": [
      {"productId": "22222222-2222-2222-2222-222222222221", "quantity": 1},
      {"productId": "22222222-2222-2222-2222-222222222222", "quantity": 2}
    ]
  }'
```

### Aprovar pagamento

```bash
curl -X POST "http://localhost:8080/api/v1/payments/orders/{ORDER_ID}/approve?externalReference=PAY-123"
```

## Estrutura

```text
src/main/java/br/com/portfolio/ecommerce
├── api
├── cart/application
├── catalog/domain
├── catalog/infrastructure
├── inventory/domain
├── inventory/infrastructure
├── order/domain
├── order/application
├── order/infrastructure
├── payment/application
├── payment/infrastructure
├── outbox/application
├── outbox/infrastructure
└── shared
```

## Roadmap para deixar ainda mais real

- JWT completo com roles ADMIN/CUSTOMER
- cadastro de clientes e endereços
- cupom de desconto
- frete
- integração real com gateway de pagamento
- consumer RabbitMQ para fulfillment
- dashboard administrativo
- testes de integração com Testcontainers
- CI/CD GitHub Actions
- Terraform/Kubernetes
