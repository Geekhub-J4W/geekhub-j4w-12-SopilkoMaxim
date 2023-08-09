CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    date_add DATE,
    current_price NUMERIC(10, 2)
    );

CREATE TABLE IF NOT EXISTS product_price_history (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    date_start DATE NOT NULL,
    date_end DATE,
    discount INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
    );

CREATE TABLE IF NOT EXISTS buyer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    buyer_id INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    delivery_method VARCHAR(50) NOT NULL,
    date_of_purchase DATE NOT NULL,
    FOREIGN KEY (buyer_id) REFERENCES buyer(id)
    );

CREATE TABLE IF NOT EXISTS order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price_by_date NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
    );
