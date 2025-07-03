# 🧑‍💼 Java JDBC Employee Database App

A simple Java CLI application to manage employee records using MySQL and JDBC.

---

## ✅ Features

- Add a new employee
- View all employees
- Update employee by ID
- Delete employee by ID

---

## 📁 Files Needed

- `EmployeeApp.java` → Java source code
- `mysql-connector-j-9.3.0.jar` → JDBC Driver (from MySQL Connector/J)

---

## 🧱 Installation & Setup

### 1. Install MySQL
- Download from: https://dev.mysql.com/downloads/installer/
- During setup, choose: **Full** or **Server only**
- Set root password (remember it)

### 2. Install MySQL Connector/J (JDBC Driver)
- Download from: https://dev.mysql.com/downloads/connector/j/
- Choose: **Platform Independent ZIP**
- Extract and copy `mysql-connector-j-9.3.0.jar` into project folder

### 3. Create Database & Table

Run the following in MySQL Workbench or CLI:

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    salary DOUBLE
);
