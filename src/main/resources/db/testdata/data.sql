-- Clean existing test data
DELETE FROM pizza_topping;
DELETE FROM delivery_item;
DELETE FROM delivery;
DELETE FROM pizza_order_stats;
DELETE FROM pizza;
DELETE FROM employee;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS employee_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS pizza_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS delivery_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS delivery_item_seq START WITH 1;
CREATE SEQUENCE IF NOT EXISTS pizza_order_stats_seq START WITH 1;

-- Insert Employees
INSERT INTO employee (id, first_name, last_name, email, position, salary, hire_date, department, task_status)
VALUES 
    (1, 'John', 'Smith', 'john.smith@pizzeria.com', 'Senior Chef', 65000.00, '2023-01-15', 'OPERATIONS', 'WAITING'),
    (2, 'Maria', 'Garcia', 'maria.garcia@pizzeria.com', 'Chef', 55000.00, '2023-02-01', 'OPERATIONS', 'WAITING'),
    (3, 'David', 'Brown', 'david.brown@pizzeria.com', 'Delivery Driver', 45000.00, '2023-03-10', 'OPERATIONS', 'WAITING'),
    (4, 'Sarah', 'Wilson', 'sarah.wilson@pizzeria.com', 'Delivery Driver', 45000.00, '2023-04-01', 'OPERATIONS', 'WAITING'),
    (5, 'Michael', 'Lee', 'michael.lee@pizzeria.com', 'Manager', 75000.00, '2023-01-01', 'OPERATIONS', 'WAITING');

-- Insert Pizzas
INSERT INTO pizza (id, name, description, price, size)
VALUES 
    (1, 'Margherita', 'Classic tomato and mozzarella', 12.99, 'MEDIUM'),
    (2, 'Pepperoni', 'Spicy pepperoni with cheese', 14.99, 'MEDIUM'),
    (3, 'Hawaiian', 'Ham and pineapple', 15.99, 'LARGE'),
    (4, 'Vegetarian', 'Mixed vegetables', 13.99, 'MEDIUM'),
    (5, 'Supreme', 'The works! All toppings', 18.99, 'LARGE');

-- Insert Pizza Toppings
INSERT INTO pizza_topping (pizza_id, topping)
VALUES 
    (1, 'Mozzarella'),
    (1, 'Tomato Sauce'),
    (1, 'Basil'),
    (2, 'Pepperoni'),
    (2, 'Mozzarella'),
    (2, 'Tomato Sauce'),
    (3, 'Ham'),
    (3, 'Pineapple'),
    (3, 'Mozzarella'),
    (4, 'Mushrooms'),
    (4, 'Bell Peppers'),
    (4, 'Onions'),
    (4, 'Olives'),
    (5, 'Pepperoni'),
    (5, 'Sausage'),
    (5, 'Mushrooms'),
    (5, 'Bell Peppers'),
    (5, 'Onions');

-- Insert Pizza Order Stats (last 3 days of data)
INSERT INTO pizza_order_stats (id, pizza_id, order_date, quantity)
VALUES 
    (1, 1, CURRENT_DATE - INTERVAL '2 DAYS', 15),  -- Margherita 2 days ago
    (2, 1, CURRENT_DATE - INTERVAL '1 DAY', 18),   -- Margherita yesterday
    (3, 1, CURRENT_DATE, 5),                       -- Margherita today
    (4, 2, CURRENT_DATE - INTERVAL '2 DAYS', 12),  -- Pepperoni 2 days ago
    (5, 2, CURRENT_DATE - INTERVAL '1 DAY', 20),   -- Pepperoni yesterday
    (6, 2, CURRENT_DATE, 8),                       -- Pepperoni today
    (7, 3, CURRENT_DATE - INTERVAL '2 DAYS', 8),   -- Hawaiian 2 days ago
    (8, 3, CURRENT_DATE - INTERVAL '1 DAY', 10),   -- Hawaiian yesterday
    (9, 3, CURRENT_DATE, 3),                       -- Hawaiian today
    (10, 4, CURRENT_DATE - INTERVAL '2 DAYS', 6),  -- Vegetarian 2 days ago
    (11, 4, CURRENT_DATE - INTERVAL '1 DAY', 8),   -- Vegetarian yesterday
    (12, 4, CURRENT_DATE, 4),                      -- Vegetarian today
    (13, 5, CURRENT_DATE - INTERVAL '2 DAYS', 10), -- Supreme 2 days ago
    (14, 5, CURRENT_DATE - INTERVAL '1 DAY', 12),  -- Supreme yesterday
    (15, 5, CURRENT_DATE, 6);                      -- Supreme today

-- Insert Deliveries
INSERT INTO delivery (id, customer_name, customer_phone, delivery_address, order_time, status, delivery_date, chef_id, delivery_person_id)
VALUES 
    (1, 'Alice Johnson', '+1234567890', '123 Main St, City', CURRENT_TIMESTAMP - INTERVAL '2 HOURS', 'DELIVERED', CURRENT_TIMESTAMP - INTERVAL '1 HOUR', 1, 3),
    (2, 'Bob Williams', '+1234567891', '456 Oak Ave, City', CURRENT_TIMESTAMP - INTERVAL '1 HOUR', 'IN_TRANSIT', CURRENT_TIMESTAMP + INTERVAL '30 MINUTES', 2, 4),
    (3, 'Carol Davis', '+1234567892', '789 Pine Rd, City', CURRENT_TIMESTAMP - INTERVAL '30 MINUTES', 'PREPARING', CURRENT_TIMESTAMP + INTERVAL '1 HOUR', 1, 3),
    (4, 'Daniel Miller', '+1234567893', '321 Elm St, City', CURRENT_TIMESTAMP, 'ORDERED', CURRENT_TIMESTAMP + INTERVAL '2 HOURS', 2, 4);

-- Insert Delivery Items
INSERT INTO delivery_item (id, delivery_id, pizza_id, quantity)
VALUES 
    (1, 1, 1, 2),  -- 2 Margheritas for delivery #1
    (2, 1, 2, 1),  -- 1 Pepperoni for delivery #1
    (3, 2, 3, 1),  -- 1 Hawaiian for delivery #2
    (4, 2, 5, 1),  -- 1 Supreme for delivery #2
    (5, 3, 4, 2),  -- 2 Vegetarian for delivery #3
    (6, 3, 1, 1),  -- 1 Margherita for delivery #3
    (7, 4, 2, 3);  -- 3 Pepperoni for delivery #4

-- Set the sequence values to continue after our explicit values
SELECT setval('employee_seq', 5);
SELECT setval('pizza_seq', 5);
SELECT setval('delivery_seq', 4);
SELECT setval('delivery_item_seq', 7);
SELECT setval('pizza_order_stats_seq', 15);
