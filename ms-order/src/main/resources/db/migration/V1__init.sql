create table orders
(
    id         bigserial primary key,
    user_id    bigint,
    status_id  bigint,
    created_at timestamp,
    summ       float
);

create table carts
(
    id      UUID primary key,
    user_id bigint,
    summ    float
);

create table orders_items
(
    id         bigserial primary key,
    order_id   bigint,
    product_id bigint,
    count      float,
    cost       float,
    sum        float
);
create table carts_items
(
    id         bigserial primary key,
    cart_id    UUID,
    product_id bigint,
    count      float,
    cost       float,
    sum        float
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
