-- noinspection SqlNoDataSourceInspectionForFile

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
       ('Bread', 28, 5);
