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
    product_item_id bigint,
    count           int
);

insert into storage_products (storage_id, product_item_id, count)
values (1, 5, 45654),
       (1, 4, 154),
       (1, 6, 4545),
       (2, 4, 762),
       (2, 1, 45),
       (2, 2, 454);