# E-commerce Enterprise Senior

Backend de e-commerce desenvolvido com foco em arquitetura enterprise, utilizando Java 21, Spring Boot, DDD, Clean Architecture, PostgreSQL, Redis, RabbitMQ, Outbox Pattern, JWT e Docker.

## Objetivo

Este projeto foi criado como estudo avançado e portfólio backend, simulando uma arquitetura próxima de sistemas corporativos reais.

O foco principal está em:

- Separação de responsabilidades
- Domínio rico
- Casos de uso bem definidos
- Persistência com Flyway
- Integração assíncrona com RabbitMQ
- Outbox Pattern
- Idempotência no checkout
- Autenticação JWT
- Observabilidade básica
- Execução completa com Docker Compose

## Stack

- Java 21
- Spring Boot 3
- PostgreSQL 16
- Redis 7
- RabbitMQ
- Flyway
- Spring Security
- JWT
- JPA/Hibernate
- Swagger/OpenAPI
- Actuator
- Prometheus
- Docker Compose
- GitHub Actions

## Arquitetura

```text
src/main/java/br/com/portfolio/ecommerce
├── identity
├── customer
├── catalog
├── inventory
├── cart
├── order
├── payment
├── shipping
├── promotion
├── notification
├── audit
├── outbox
└── shared
````

## Bounded Contexts

| Contexto     | Responsabilidade                    |
| ------------ | ----------------------------------- |
| identity     | Autenticação e geração de token JWT |
| customer     | Cadastro e validação de cliente     |
| catalog      | Produtos e preços                   |
| inventory    | Estoque e reserva                   |
| cart         | Carrinho temporário                 |
| order        | Checkout e ciclo de vida do pedido  |
| payment      | Aprovação/rejeição de pagamento     |
| shipping     | Cálculo de frete                    |
| promotion    | Cupom e desconto                    |
| notification | Notificações assíncronas            |
| audit        | Auditoria de ações                  |
| outbox       | Publicação confiável de eventos     |

## Padrões aplicados

* DDD
* Clean Architecture
* SOLID
* Repository Pattern
* Use Case Pattern
* Outbox Pattern
* Idempotency Key
* Pessimistic Lock
* JWT Authentication
* Event-driven Architecture
* Database Migration com Flyway

## Como executar

```bash
docker compose up --build
```

## Portas

| Serviço             | URL                                                                                        |
| ------------------- | ------------------------------------------------------------------------------------------ |
| API                 | [http://localhost:8081](http://localhost:8081)                                             |
| Swagger             | [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) |
| Actuator Health     | [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)             |
| Prometheus Metrics  | [http://localhost:8081/actuator/prometheus](http://localhost:8081/actuator/prometheus)     |
| PostgreSQL          | localhost:5433                                                                             |
| Redis               | localhost:6380                                                                             |
| RabbitMQ Management | [http://localhost:15673](http://localhost:15673)                                           |

## Credenciais de teste

```text
email: admin@portfolio.com
password: admin123
```

## Fluxo principal

1. Usuário faz login e recebe JWT
2. Cliente consulta produtos
3. Cliente realiza checkout com `Idempotency-Key`
4. Sistema valida cliente, produto e estoque
5. Estoque é reservado
6. Pedido é criado com status `PENDING_PAYMENT`
7. Evento `order.created` é salvo na outbox
8. Job publica evento no RabbitMQ
9. Pagamento é aprovado
10. Pedido segue no fluxo assíncrono

## Login

```powershell
$body = @{
    email = "admin@portfolio.com"
    password = "admin123"
} | ConvertTo-Json

Invoke-RestMethod `
    -Uri "http://localhost:8081/api/v1/auth/login" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body
```

## Criar pedido

```json
{
  "customerId": "11111111-1111-1111-1111-111111111111",
  "couponCode": null,
  "shippingZipCode": "13000-000",
  "items": [
    {
      "productId": "22222222-2222-2222-2222-222222222221",
      "quantity": 1
    },
    {
      "productId": "22222222-2222-2222-2222-222222222222",
      "quantity": 2
    }
  ]
}
```

Header obrigatório:

```text
Idempotency-Key: pedido-portfolio-001
```

## Aprovar pagamento

```text
POST /api/v1/payments/orders/{orderId}/approve?externalReference=PAY-123
```

## Verificar banco

```bash
docker exec -it ecommerce_v2_postgres psql -U ecommerce -d ecommerce
```

```sql
select id,status,total,idempotency_key from orders;
select * from order_items;
select product_id,available_quantity from inventory;
select event_type,payload,published from outbox_events;
```

## Observabilidade

O projeto possui:

* Actuator
* Prometheus endpoint
* Correlation ID por request
* Logging estruturado com MDC

Header suportado:

```text
X-Correlation-Id
```

## Documentação

Arquivos complementares:

```text
docs/architecture.md
docs/order-flow.md
```

## CI

Pipeline GitHub Actions:

```text
.github/workflows/ci.yml
```

Executa:

* Checkout
* Setup Java 21
* Maven test/build

## Status atual

Funcionalidades validadas:

* API sobe via Docker
* Swagger disponível
* Login JWT funcionando
* Checkout funcionando
* Idempotência validada
* Pagamento aprovado
* PostgreSQL persistindo dados
* Outbox gravando e publicando evento
* RabbitMQ inicializado
* Flyway aplicando migration

## Roadmap

* DTOs request/response dedicados
* MapStruct
* Testcontainers
* Jacoco
* SonarQube
* Trivy
* Saga Pattern
* CQRS
* Frontend React
* Kubernetes/Helm