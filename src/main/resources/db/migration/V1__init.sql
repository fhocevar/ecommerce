CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR(160) NOT NULL,
    email VARCHAR(180) NOT NULL UNIQUE,
    document VARCHAR(30) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE products (
    id UUID PRIMARY KEY,
    sku VARCHAR(80) NOT NULL UNIQUE,
    name VARCHAR(180) NOT NULL,
    description TEXT,
    price NUMERIC(18,2) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE inventory_items (
    product_id UUID PRIMARY KEY REFERENCES products(id),
    available_quantity INT NOT NULL,
    reserved_quantity INT NOT NULL DEFAULT 0,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL REFERENCES customers(id),
    status VARCHAR(40) NOT NULL,
    total_amount NUMERIC(18,2) NOT NULL,
    idempotency_key VARCHAR(120) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE order_items (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders(id),
    product_id UUID NOT NULL REFERENCES products(id),
    sku VARCHAR(80) NOT NULL,
    product_name VARCHAR(180) NOT NULL,
    quantity INT NOT NULL,
    unit_price NUMERIC(18,2) NOT NULL,
    total_price NUMERIC(18,2) NOT NULL
);

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL UNIQUE REFERENCES orders(id),
    status VARCHAR(40) NOT NULL,
    provider VARCHAR(80) NOT NULL,
    external_reference VARCHAR(120),
    amount NUMERIC(18,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE outbox_events (
    id UUID PRIMARY KEY,
    aggregate_type VARCHAR(80) NOT NULL,
    aggregate_id UUID NOT NULL,
    event_type VARCHAR(120) NOT NULL,
    payload JSONB NOT NULL,
    status VARCHAR(30) NOT NULL,
    attempts INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    processed_at TIMESTAMP NULL
);

CREATE TABLE audit_log (
    id UUID PRIMARY KEY,
    actor VARCHAR(120),
    action VARCHAR(120) NOT NULL,
    resource VARCHAR(120) NOT NULL,
    resource_id VARCHAR(120),
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO customers(id, name, email, document) VALUES
('11111111-1111-1111-1111-111111111111', 'Cliente Portfolio', 'cliente@portfolio.com', '12345678900');

INSERT INTO products(id, sku, name, description, price, active) VALUES
('22222222-2222-2222-2222-222222222221', 'NOTEBOOK-PRO-14', 'Notebook Pro 14', 'Notebook para produtividade e desenvolvimento', 8999.90, true),
('22222222-2222-2222-2222-222222222222', 'HEADSET-WIRELESS', 'Headset Wireless', 'Headset bluetooth com cancelamento de ruido', 799.90, true),
('22222222-2222-2222-2222-222222222223', 'DOCK-USB-C', 'Dock USB-C Enterprise', 'Dock station com HDMI, rede e USB-C', 599.90, true);

INSERT INTO inventory_items(product_id, available_quantity, reserved_quantity) VALUES
('22222222-2222-2222-2222-222222222221', 20, 0),
('22222222-2222-2222-2222-222222222222', 100, 0),
('22222222-2222-2222-2222-222222222223', 50, 0);
