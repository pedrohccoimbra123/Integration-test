CREATE TABLE product(
    id SERIAL PRIMARY KEY NOT NULL,
    product_name TEXT NOT NULL,
    price FLOAT NOT NULL,
    description TEXT,
    stock integer
);