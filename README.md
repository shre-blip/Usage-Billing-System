## Usage and Billing System

## About

This is a simple Java project to manage shared resources, track their usage, and calculate bills.

## Features

View available resources and their capacity.

Start and stop resource usage.

Prevent usage when a resource has reached its capacity.

Record start and end times.

Calculate the bill using the first-hour and additional-hour rates.

View usage history.

## Technologies

Java

SQL table design

## Project Files

src/BillingSystem.java - Runs the menu and handles user actions.

src/Resource.java - Stores resource details and checks capacity.

src/Service.java - Stores service prices.

src/UsageRecord.java - Stores usage times and calculates the bill.

database/schema.sql - Contains the SQL table definitions and sample data.

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

## Billing

The first billable hour uses the first-hour price. Each extra billable hour uses the additional-hour price. A partial hour is rounded up to a full hour.

## Note

The Java program currently stores usage records in memory. The SQL file provides the database structure, but it is not connected to the Java program.