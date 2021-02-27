DROP TABLE IF EXISTS products CASCADE;
create table if not exists products
(
    id    bigint auto_increment primary key,
    title varchar(255)   null,
    cost  decimal(10, 2) null
);
INSERT INTO products (title, cost)
VALUES ('Book', 250.50),
       ('Phone', 69999.99),
       ('Notebook', 89990.00);
