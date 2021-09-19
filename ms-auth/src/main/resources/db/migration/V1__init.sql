create table users
(
    id       bigserial primary key,
    login    varchar(255),
    password varchar(255)
);

insert into users (login, password)
values ('admin', '$2y$10$XgUB7dW3.DDyfhgDen6whepgGabBCto5GZj7FJahjshBHa4AFcr26');

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);


insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2);
