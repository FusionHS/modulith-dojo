-- Clean existing test data
DELETE FROM pizza_topping;
DELETE FROM delivery_item;
DELETE FROM delivery;
DELETE FROM pizza;
DELETE FROM employee;

-- Reset sequences
ALTER SEQUENCE pizza_seq RESTART WITH 1;
ALTER SEQUENCE employee_seq RESTART WITH 1;
ALTER SEQUENCE delivery_seq RESTART WITH 1;
ALTER SEQUENCE delivery_item_seq RESTART WITH 1;

-- Create variables for pizza IDs
DO $$
DECLARE
    margherita_id BIGINT;
    pepperoni_id BIGINT;
    hawaiian_id BIGINT;
    chef_id BIGINT;
    delivery_person_id BIGINT;
BEGIN
    -- Insert pizzas and store their IDs
    INSERT INTO pizza (id, name, description, price, size)
    VALUES (nextval('pizza_seq'), 'Margherita', 'Classic Italian pizza with tomato sauce and mozzarella', 12.99, 'MEDIUM')
    RETURNING id INTO margherita_id;

    INSERT INTO pizza (id, name, description, price, size)
    VALUES (nextval('pizza_seq'), 'Pepperoni', 'American classic with lots of pepperoni', 14.99, 'LARGE')
    RETURNING id INTO pepperoni_id;

    INSERT INTO pizza (id, name, description, price, size)
    VALUES (nextval('pizza_seq'), 'Hawaiian', 'Controversial pizza with ham and pineapple', 13.99, 'MEDIUM')
    RETURNING id INTO hawaiian_id;

    -- Insert toppings using the stored IDs
    INSERT INTO pizza_topping (pizza_id, topping) VALUES
    (margherita_id, 'Tomato Sauce'), (margherita_id, 'Mozzarella'), (margherita_id, 'Basil'),
    (pepperoni_id, 'Tomato Sauce'), (pepperoni_id, 'Mozzarella'), (pepperoni_id, 'Pepperoni'),
    (hawaiian_id, 'Tomato Sauce'), (hawaiian_id, 'Mozzarella'), (hawaiian_id, 'Ham'), (hawaiian_id, 'Pineapple');

    -- Insert employees and store IDs for delivery
    INSERT INTO employee (id, first_name, last_name, email, position, salary, hire_date, department)
    VALUES 
    (nextval('employee_seq'), 'Gordon', 'Ramsay', 'gordon.ramsay@fusionhs.com', 'Head Chef', 75000, '2025-01-01', 'OPERATIONS')
    RETURNING id INTO chef_id;

    -- Insert delivery driver and get their ID
    INSERT INTO employee (id, first_name, last_name, email, position, salary, hire_date, department)
    VALUES 
    (nextval('employee_seq'), 'Jamie', 'Oliver', 'jamie.oliver@fusionhs.com', 'Sous Chef', 60000, '2025-01-01', 'OPERATIONS');

    INSERT INTO employee (id, first_name, last_name, email, position, salary, hire_date, department)
    VALUES 
    (nextval('employee_seq'), 'John', 'Doe', 'john.doe@fusionhs.com', 'Delivery Driver', 35000, '2025-01-01', 'OPERATIONS')
    RETURNING id INTO delivery_person_id;

    -- Insert a sample delivery
    INSERT INTO delivery (
        id, 
        customer_name, 
        customer_phone, 
        delivery_address, 
        order_time, 
        delivery_date, 
        status, 
        chef_id, 
        delivery_person_id
    )
    VALUES (
        nextval('delivery_seq'),
        'Alice Johnson',
        '+1-555-0123',
        '123 Pizza Lane, Foodville, FD 12345',
        NOW(),
        NOW() + interval '30 minutes',
        'PREPARING',
        chef_id,
        delivery_person_id
    );

    -- Insert delivery items
    INSERT INTO delivery_item (id, delivery_id, pizza_id, quantity)
    VALUES 
    (nextval('delivery_item_seq'), currval('delivery_seq'), margherita_id, 1),
    (nextval('delivery_item_seq'), currval('delivery_seq'), pepperoni_id, 2);
END $$; 