-- DATABASE SETUP SCRIPT
-- Project: University Placement Management System
-- Run this script in MySQL to create the database and generate 500 dummy records.

-- 1. Create and Select Database
CREATE DATABASE IF NOT EXISTS university_placement;
USE university_placement;

-- 2. Create Student Table
-- Stores all student details. Clears old data if table exists.
DROP TABLE IF EXISTS Student_Master;
CREATE TABLE Student_Master (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    cpi DECIMAL(4, 2),
    backlogs INT
);

-- 3. Create Temporary Tables for Name Generation
CREATE TEMPORARY TABLE First_Names (fname VARCHAR(50));
CREATE TEMPORARY TABLE Last_Names (lname VARCHAR(50));

-- 4. Add Common Names (25 First x 20 Last = 500 Combinations)
INSERT INTO First_Names VALUES 
('Aarav'), ('Vivaan'), ('Aditya'), ('Vihaan'), ('Arjun'), 
('Sai'), ('Reyansh'), ('Aryan'), ('Krishna'), ('Ishaan'),
('Ananya'), ('Diya'), ('Gauri'), ('Isha'), ('Kavya'), 
('Meera'), ('Neha'), ('Pari'), ('Riya'), ('Sanya'),
('Rohan'), ('Vikram'), ('Amit'), ('Rahul'), ('Sneha');

INSERT INTO Last_Names VALUES 
('Sharma'), ('Verma'), ('Gupta'), ('Singh'), ('Kumar'), 
('Mehta'), ('Joshi'), ('Patel'), ('Reddy'), ('Nair'), 
('Das'), ('Kapoor'), ('Malhotra'), ('Bhat'), ('Rao'), 
('Saxena'), ('Iyer'), ('Chopra'), ('Jain'), ('Mishra');

-- 5. Generate 500 Students
-- Combines names, creates emails, and assigns random CPI (5-10) and Backlogs (0-5).
INSERT INTO Student_Master (name, email, cpi, backlogs)
SELECT 
    CONCAT(f.fname, ' ', l.lname),
    LOWER(CONCAT(f.fname, '.', l.lname, '@gehu.ac.in')),
    ROUND(5.0 + (RAND() * 5.0), 2),
    FLOOR(RAND() * 6)
FROM First_Names f
CROSS JOIN Last_Names l;

-- 6. Cleanup & Verification
DROP TEMPORARY TABLE First_Names;
DROP TEMPORARY TABLE Last_Names;

SELECT * FROM Student_Master LIMIT 10; -- Show first 10 rows to verify
SELECT COUNT(*) AS 'Total Students Generated' FROM Student_Master; -- Should show 500