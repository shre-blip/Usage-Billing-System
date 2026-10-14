 

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

