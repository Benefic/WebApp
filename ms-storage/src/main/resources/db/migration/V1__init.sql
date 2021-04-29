create table storages
(
    id       bigserial primary key,
    title    varchar(255),
    location varchar(255)
);

insert into storages (title, location)
values ('Moscow', 'Moscow, TZB'),
       ('MO', 'M4 123 km');


create table storage_products
(
    storage_id      bigint,
    product_item_id bigint
);

insert into storage_products (storage_id, product_item_id)
values (1, 5),
       (1, 4),
       (1, 6),
       (2, 4),
       (2, 1),
       (2, 2);