create table orders
(
    id          bigserial primary key,
    customer_id bigint
);

create table orders_products
(
    order_id        bigint,
    product_item_id bigint
);

create table order_statuses
(
    id    bigserial primary key,
    title varchar(50)
);

insert into order_statuses (title)
values ('Created'),
       ('Confirmed'),
       ('On delivery'),
       ('Closed');