# Usage and Billing System

# About

This is a simple Java project to manage shared resources, track their usage, and calculate bills.The Usage & Billing System is a Java-based application designed to manage shared resources such as meeting rooms and workstations.The system allows users to start and stop resource usage, manages resource capacity, records usage timestamps, and calculates bills based on the duration of usage.The application ensures that a resource is never used beyond its maximum permitted capacity.

# Features

View available resources and their capacity.

Start and stop resource usage.

Prevent usage when a resource has reached its capacity.

Record start and end times.

Calculate the bill using the first-hour and additional-hour rates.

View usage history.

# Technologies

Java

SQL table design(just schema not connected)

# Project Files

src/BillingSystem.java - Runs the menu and handles user actions.

src/Resource.java - Stores resource details and checks capacity.

src/Service.java - Stores service prices.

src/UsageRecord.java - Stores usage times and calculates the bill.

database/schema.sql - Contains the SQL table definitions and sample data.

# Technology Stack

- **Programming Language:** Java
- **Data Storage:** In-memory storage using Java collections
- **Database Design:** SQL schema provided separately
- **Development Environment:** Visual Studio Code
- **Version Control:** Git and GitHub
The application uses standard Java libraries and does not require external frameworks or libraries.

# Project Structure

UsageBillingSystem/
│
├── src/
│   ├── BillingSystem.java
│   ├── Resource.java
│   ├── Service.java
│   └── UsageRecord.java
│
├── database/
│   └── schema.sql
│
├── README.md
└── .gitignore


# Data Structures Used

The application uses Java classes and collections to organize resource information, service pricing, and usage records.

## Resource Class

The `Resource` class represents a shared resource, such as a meeting room or workstation.

It stores information such as:
- Resource ID
- Resource name
- Maximum capacity
- Associated service information

**Purpose:** To represent each resource and help manage its availability and capacity.

## Service Class

The `Service` class represents the service offered for a resource.

It stores:
- Service ID
- Service name
- First-hour price
- Additional-hour price

**Purpose:** To maintain the pricing rules used to calculate bills.

## UsageRecord Class

The `UsageRecord` class represents an individual resource usage session.

It stores:
- Usage record ID
- User name
- Resource information
- Start time
- End time
- Usage and billing information

**Purpose:** To track resource usage from the start of a session until it ends and calculate the applicable bill.

## ArrayList

The application uses Java collections to maintain resource and usage information during execution.

The `ArrayList` data structure is used for storing collections of objects, such as:

- Resources available in the system.
- Usage records created by users.

Example:

```java
List<Resource> resources = new ArrayList<>();
List<UsageRecord> allUsages = new ArrayList<>()

## How to Run

Open a terminal in the project folder and run:

javac -d out src\*.java
java -cp out BillingSystem

## How to test

1. Run the program and choose `1` to see the sample resources.
2. Choose `2`, select resource ID `2`, and enter a user name. Start another session on resource ID `2`; the second request should be rejected because its capacity is one.
3. Start sessions on resource ID `1` until its capacity of two is reached. A further request should be rejected.
4. Choose `3` and stop an active usage using its record ID. The program prints the bill and frees a slot.
5. Choose `4` to review active and completed usage records.


##  Note

The Java program currently stores usage records in memory. The SQL file provides the database structure, but it is not connected to the Java program.
