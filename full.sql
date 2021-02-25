BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    'id'    bigint AUTO_INCREMENT,
    'title' VARCHAR(255),
    'cost'  DECIMAL(10, 2),
    PRIMARY KEY ('id')
);
INSERT INTO products ('title', 'cost')
VALUES ('Book', 150.50),
       ('Phone', 59999.99),
       ('Notebook', 79990.00);

COMMIT;