create table users
(
    id       bigserial primary key,
    name     varchar(255),
    role     varchar(50),
    password varchar(255)
);

insert into users (name, role, password)
values ('Admin', 'ROLE_ADMIN', '$2y$10$XgUB7dW3.DDyfhgDen6whepgGabBCto5GZj7FJahjshBHa4AFcr26');
