create table products
(
    id    bigserial primary key,
    title varchar(255),
    cost  decimal(10, 2)
);
insert into products (title, cost)
values ('Book', 250.5),
       ('Notebook', 69990),
       ('Phone', 59990),
       ('Milk', 59),
       ('Bread', 28),
       ('Butter', 150.5),
       ('Box', 5.9),
       ('TV', 159990),
       ('Knife', 55),
       ('Glass', 30),
       ('Cup', 68.5),
       ('Spoon', 15.5),
       ('Fork', 16.9),
       ('Table', 5990),
       ('Door', 3800),
       ('Disk', 120),
       ('Picture', 260),
       ('Water', 38),
       ('Apple', 5.5),
       ('Headphones', 150.5);
