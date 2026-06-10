# Order Flow

```mermaid
sequenceDiagram
    participant Client
    participant OrderAPI
    participant Inventory
    participant Outbox
    participant RabbitMQ
    participant PaymentAPI

    Client->>OrderAPI: POST /orders/checkout
    OrderAPI->>Inventory: Reserve stock
    OrderAPI->>Outbox: Store order.created
    Outbox->>RabbitMQ: Publish event
    Client->>PaymentAPI: POST /payments/orders/{id}/approve