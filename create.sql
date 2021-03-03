create table if not exists customers
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

INSERT INTO customers (name)
VALUES ('Bob');
INSERT INTO customers (name)
VALUES ('Mike');
INSERT INTO customers (name)
VALUES ('Nick');
INSERT INTO customers (name)
VALUES ('Stupid');

create table if not exists customers_products
(
    customer_id   bigint                              not null,
    product_id    bigint                              not null,
    buy_cost      decimal(10, 2)                      null,
    buy_timestamp timestamp default CURRENT_TIMESTAMP not null
);

INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (1, 1, 1100.00, '2021-02-28 19:39:41');
INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (1, 5, 1200.00, '2021-02-28 19:39:41');
INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (2, 1, 2100.00, '2021-02-28 19:39:41');
INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (2, 5, 2500.00, '2021-02-28 19:39:41');
INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (4, 3, 43000.00, '2021-02-28 19:39:42');
INSERT INTO customers_products (customer_id, product_id, buy_cost, buy_timestamp)
VALUES (1, 1, 11000.00, '2021-02-28 19:39:59');

create table if not exists products
(
    id    bigint auto_increment
        primary key,
    title varchar(255)   null,
    cost  decimal(10, 2) null
);

INSERT INTO products (title, cost)
VALUES ('Book', 250.50);
INSERT INTO products (title, cost)
VALUES ('Notebook', 89990.00);
INSERT INTO products (title, cost)
VALUES ('Phone', 3456.00);
INSERT INTO products (title, cost)
VALUES ('Headphones', 5555.55);


