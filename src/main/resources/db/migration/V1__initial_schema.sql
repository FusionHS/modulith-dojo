-- Create sequences
CREATE SEQUENCE IF NOT EXISTS pizza_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS employee_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS delivery_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS delivery_item_seq START WITH 1 INCREMENT BY 50;

-- Create Spring Modulith Event Publication table
CREATE TABLE event_publication (
    id UUID NOT NULL,
    listener_id VARCHAR(255) NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    serialized_event TEXT NOT NULL,
    publication_date TIMESTAMP NOT NULL,
    completion_date TIMESTAMP,
    PRIMARY KEY (id)
);

-- Create Pizza table
CREATE TABLE pizza (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    size VARCHAR(20) NOT NULL
);

-- Create Pizza Topping table
CREATE TABLE pizza_topping (
    pizza_id BIGINT NOT NULL,
    topping VARCHAR(255),
    FOREIGN KEY (pizza_id) REFERENCES pizza(id)
);

-- Create Employee table
CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    position VARCHAR(255) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    hire_date DATE NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- Create Delivery table
CREATE TABLE delivery (
    id BIGINT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(50) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    order_time TIMESTAMP NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    chef_id BIGINT NOT NULL,
    delivery_person_id BIGINT NOT NULL,
    FOREIGN KEY (chef_id) REFERENCES employee(id),
    FOREIGN KEY (delivery_person_id) REFERENCES employee(id)
);

-- Create Delivery Item table
CREATE TABLE delivery_item (
    id BIGINT PRIMARY KEY,
    delivery_id BIGINT NOT NULL,
    pizza_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (delivery_id) REFERENCES delivery(id),
    FOREIGN KEY (pizza_id) REFERENCES pizza(id)
); 