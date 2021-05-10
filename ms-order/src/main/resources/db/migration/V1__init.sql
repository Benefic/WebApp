create table orders
(
    id        bigserial primary key,
    user_id   bigint,
    status_id bigint
);

create table orders_items
(
    order_id        bigint,
    product_item_id bigint,
    count           float,
    cost            float,
    sum             float
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