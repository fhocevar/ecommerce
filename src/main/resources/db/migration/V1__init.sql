create sequence if not exists order_items_seq start with 1 increment by 50;

create table user_accounts (
    id uuid primary key,
    email varchar(180) not null unique,
    password_hash varchar(255) not null,
    role varchar(50) not null
);

create table customers (
    id uuid primary key,
    name varchar(180) not null,
    email varchar(180) not null,
    active boolean not null
);

create table products (
    id uuid primary key,
    sku varchar(80) not null unique,
    name varchar(180) not null,
    price numeric(12,2) not null,
    active boolean not null
);

create table inventory (
    product_id uuid primary key,
    available_quantity int not null,
    version bigint not null default 0
);

create table orders (
    id uuid primary key,
    customer_id uuid not null,
    status varchar(40) not null,
    subtotal numeric(12,2) not null,
    discount numeric(12,2) not null,
    shipping numeric(12,2) not null,
    total numeric(12,2) not null,
    idempotency_key varchar(120) unique,
    created_at timestamp with time zone not null
);

create table order_items (
    id bigint primary key default nextval('order_items_seq'),
    order_id uuid not null references orders(id),
    product_id uuid not null,
    product_name varchar(180) not null,
    quantity int not null,
    unit_price numeric(12,2) not null
);

create table outbox_events (
    id uuid primary key,
    event_type varchar(100) not null,
    occurred_at timestamp with time zone not null,
    payload text not null,
    published boolean not null,
    created_at timestamp with time zone not null
);

create table audit_logs (
    id uuid primary key,
    action varchar(120) not null,
    details text,
    created_at timestamp with time zone not null
);

insert into user_accounts(id,email,password_hash,role)
values (
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'admin@portfolio.com',
    '$2a$10$OzO3AeXZ8d00Hhb3oYyk3OuY4QORkkKxEvI9/Nu8JW/Fv4bPdgd8O',
    'ADMIN'
);

insert into customers(id,name,email,active)
values (
    '11111111-1111-1111-1111-111111111111',
    'Cliente Demo',
    'cliente@portfolio.com',
    true
);

insert into products(id,sku,name,price,active)
values
    (
        '22222222-2222-2222-2222-222222222221',
        'NOTEBOOK-PRO',
        'Notebook Pro 16',
        8999.90,
        true
    ),
    (
        '22222222-2222-2222-2222-222222222222',
        'MOUSE-WIRELESS',
        'Mouse Wireless',
        199.90,
        true
    ),
    (
        '22222222-2222-2222-2222-222222222223',
        'KEYBOARD-MECH',
        'Teclado Mecânico',
        499.90,
        true
    );

insert into inventory(product_id,available_quantity,version)
values
    ('22222222-2222-2222-2222-222222222221', 10, 0),
    ('22222222-2222-2222-2222-222222222222', 100, 0),
    ('22222222-2222-2222-2222-222222222223', 50, 0);