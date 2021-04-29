create table storages
(
    id       bigserial primary key,
    title    varchar(255),
    location varchar(255)
);

insert into storages (title, location)
values ('Moscow', 'Moscow, TZB'),
       ('MO', 'M4 123 km');
