create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

insert into categories (title)
values ('First'),
       ('Second'),
       ('Third'),
       ('Fourth'),
       ('Fifth');


create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigserial,
    cost        decimal(10, 2)
);

insert into products (title, cost, category_id)
values ('Book', 250.5, 1),
       ('Notebook', 69990, 2),
       ('Phone', 59990, 3),
       ('Milk', 59, 4),
       ('Bread', 28, 5),
       ('Butter', 150.5, 2),
       ('Box', 5.9, 2),
       ('TV', 159990, 4),
       ('Knife', 55, 1),
       ('Glass', 30, 3),
       ('Cup', 68.5, 3),
       ('Spoon', 15.5, 4),
       ('Fork', 16.9, 4),
       ('Table', 5990, 2),
       ('Door', 3800, 3),
       ('Disk', 55, 2),
       ('Picture', 260, 4),
       ('Water', 38, 4),
       ('Apple', 5.5, 1),
       ('Headphones', 150.5, 2);

create table products_items
(
    id         bigserial primary key,
    product_id bigint,
    serial     varchar(50) default ''
);

insert into products_items (product_id, serial)
values (1),
       (2),
       (3, '456515/55'),
       (3, '123845/88'),
       (3),
       (4),
       (5),
       (5, '2654954/55/6'),
       (5),
       (5, '6845118/98/4'),
       (6);
