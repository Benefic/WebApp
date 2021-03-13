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
       ('Box', 15.9, 3),
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
       ('Headphones', 150.5, 2),
       ('Book', 250.5, 1),
       ('Notebook', 69990, 2),
       ('Phone', 59990, 3),
       ('Milk', 59, 1),
       ('Bread', 28, 2),
       ('Butter', 150.5, 5),
       ('Box', 5.9, 1),
       ('TV', 159990, 3),
       ('Knife', 55, 1),
       ('Glass', 30, 3),
       ('Cup', 68.5, 1),
       ('Spoon', 15.5, 4),
       ('Fork', 16.9, 5),
       ('Table', 5990, 2),
       ('Door', 3800, 3),
       ('Disk', 120, 1),
       ('Picture', 260, 3),
       ('Water', 38, 1),
       ('Apple', 5.5, 3),
       ('Headphones', 150.5, 4);
