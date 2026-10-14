-- Basic relational design for storing the same information in an SQL database.
-- The assignment permits in-memory storage and does not require a database connection.
-- Prices are stored in rupees. DECIMAL is used for money rather than floating point.

CREATE TABLE Services (
    service_id INT PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL,
    first_hour_cost DECIMAL(10,2) NOT NULL,
    additional_hour_cost DECIMAL(10,2) NOT NULL
);

CREATE TABLE Resources (
    resource_id INT PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    service_id INT NOT NULL,
    FOREIGN KEY (service_id) REFERENCES Services(service_id)
);

CREATE TABLE UsageRecords (
    usage_id INT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    resource_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NULL,
    billable_hours INT NULL,
    final_bill DECIMAL(10,2) NULL,
    FOREIGN KEY (resource_id) REFERENCES Resources(resource_id)
);

-- Example setup matching the command-line application's sample data.
INSERT INTO Services VALUES
(1, 'Meeting Room', 30.00, 10.00),
(2, 'Workstation', 20.00, 8.00);

INSERT INTO Resources VALUES
(1, 'Meeting Room A', 2, 1),
(2, 'Workstation 1', 1, 2);

-- Example query to check capacity use from active sessions:
-- SELECT r.resource_name, r.capacity, COUNT(u.usage_id) AS active_users
-- FROM Resources r
-- LEFT JOIN UsageRecords u
--   ON r.resource_id = u.resource_id AND u.end_time IS NULL
-- GROUP BY r.resource_id, r.resource_name, r.capacity;
