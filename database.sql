-- =========================================================
-- STUDENT MANAGEMENT SYSTEM
-- DATABASE SETUP
-- =========================================================

CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;


-- =========================================================
-- USERS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    student_id INT NULL
);


-- =========================================================
-- STUDENTS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20)
);


-- =========================================================
-- ATTENDANCE TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    attendance_date DATE NOT NULL,
    status VARCHAR(10) NOT NULL,

    FOREIGN KEY (student_id)
        REFERENCES students(id)
);


-- =========================================================
-- MARKS TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS marks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    marks INT NOT NULL,
    total_marks INT NOT NULL,

    FOREIGN KEY (student_id)
        REFERENCES students(id)
);


-- =========================================================
-- FEES TABLE
-- =========================================================

CREATE TABLE IF NOT EXISTS fees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    payment_method VARCHAR(30),

    FOREIGN KEY (student_id)
        REFERENCES students(id)
);


-- =========================================================
-- SAMPLE STUDENT
-- =========================================================

INSERT INTO students
(name, age, course, email, phone)
VALUES
('Amit', 21, 'Computer Engineering', 'amit@gmail.com', '9876543210');


-- =========================================================
-- SAMPLE USERS
-- =========================================================

INSERT INTO users
(username, password, role, student_id)
VALUES
('admin', 'admin123', 'ADMIN', NULL);

INSERT INTO users
(username, password, role, student_id)
VALUES
('student1', 'student123', 'STUDENT', 1);


-- =========================================================
-- SAMPLE ATTENDANCE
-- =========================================================

INSERT INTO attendance
(student_id, attendance_date, status)
VALUES
(1, '2026-08-23', 'Present');


-- =========================================================
-- SAMPLE MARKS
-- =========================================================

INSERT INTO marks
(student_id, subject, marks, total_marks)
VALUES
(1, 'Java', 89, 100);


-- =========================================================
-- SAMPLE FEES
-- =========================================================

INSERT INTO fees
(student_id, amount, payment_date, payment_status, payment_method)
VALUES
(1, 50000.00, '2026-08-23', 'Paid', 'UPI');

INSERT INTO fees
(student_id, amount, payment_date, payment_status, payment_method)
VALUES
(1, 25000.00, '2026-08-26', 'Paid', 'UPI');


-- =========================================================
-- VERIFY DATABASE
-- =========================================================

SELECT * FROM users;

SELECT * FROM students;

SELECT * FROM attendance;

SELECT * FROM marks;

SELECT * FROM fees;