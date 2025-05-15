-- Clean existing test data
DELETE FROM employee;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS employee_seq START WITH 1;

-- Insert Employees
INSERT INTO employee (id, first_name, last_name, email, position, salary, hire_date, department, task_status)
VALUES 
    (1, 'John', 'Smith', 'john.smith@pizzeria.com', 'Senior Chef', 65000.00, '2023-01-15', 'OPERATIONS', 'WAITING'),
    (2, 'Maria', 'Garcia', 'maria.garcia@pizzeria.com', 'Chef', 55000.00, '2023-02-01', 'OPERATIONS', 'WAITING'),
    (3, 'David', 'Brown', 'david.brown@pizzeria.com', 'Delivery Driver', 45000.00, '2023-03-10', 'OPERATIONS', 'WAITING'),
    (4, 'Sarah', 'Wilson', 'sarah.wilson@pizzeria.com', 'Delivery Driver', 45000.00, '2023-04-01', 'OPERATIONS', 'WAITING'),
    (5, 'Michael', 'Lee', 'michael.lee@pizzeria.com', 'Manager', 75000.00, '2023-01-01', 'OPERATIONS', 'WAITING');

-- Set the sequence values to continue after our explicit values
SELECT setval('employee_seq', 5);
