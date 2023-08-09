INSERT INTO product (id, name, manufacturer, date_add, current_price)
VALUES
    (101, 'Product A', 'Manufacturer X', '2023-01-01', 100.00),
    (102, 'Product B', 'Manufacturer Y', '2023-01-02', 150.00),
    (103, 'Product C', 'Manufacturer Z', '2023-01-03', 200.00);

INSERT INTO product_price_history (id, product_id, price, date_start, date_end, discount)
VALUES
   -- (201, 101, 100.00, '2023-01-01', NULL, 0),
    (202, 101, 90.00, '2023-01-10', NULL, 10),
    (203, 102, 150.00, '2023-01-02', NULL, 0),
    (204, 103, 200.00, '2023-01-03', NULL, 0);

INSERT INTO buyer (id, first_name, last_name, address)
VALUES
    (301, 'Thor', 'Odinson', 'Asgard'),
    (302, 'Lokky', 'Odinson', 'New York');

INSERT INTO orders (id, buyer_id, address, date_of_purchase, delivery_method)
VALUES
    (401, 301, 'Asgard', '2023-01-15', 'Standard Delivery'),
    (402, 302, 'New York', '2023-01-16', 'Express Delivery');

INSERT INTO order_items (id, order_id, product_id, quantity, price_by_date)
VALUES
    (501, 401, 102, 1, 90.00),
    (502, 402, 103, 1, 150.00),
    (503, 401, 101, 1, 200.00);
