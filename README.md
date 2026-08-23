# Student Management System

A console-based Student Management System developed using Java, JDBC, and MySQL.

## 📌 About

This project allows users to manage student records through a simple menu-driven application.

The application performs CRUD operations:

- Add Student
- View All Students
- Search Student
- Update Student
- Delete Student

## 🛠️ Technologies Used

- Java
- OOP
- JDBC
- MySQL
- Eclipse IDE

## ✨ Features

### 1. Add Student
Add a new student with:
- Name
- Age
- Course
- Email
- Phone

### 2. View All Students
Display all student records stored in the MySQL database.

### 3. Search Student
Search for a student using their ID.

### 4. Update Student
Update existing student information.

### 5. Delete Student
Delete a student record using their ID.

### 6. Input Validation
The application validates:
- Name
- Age
- Email
- Phone number
- Menu choice

## 🗄️ Database

Database:

`student_db`

Table:

`students`

The table contains:

| Column | Description |
|---|---|
| id | Student ID |
| name | Student Name |
| age | Student Age |
| course | Student Course |
| email | Student Email |
| phone | Student Phone |

## 📂 Project Structure

```text
StudentManagementSystem
│
├── studentmanagement.dao
│   └── StudentDAO.java
│
├── studentmanagement.main
│   ├── Main.java
│   └── TestStudent.java
│
├── studentmanagement.model
│   └── Student.java
│
└── studentmanagement.util
    └── └── DBConnection.java
```

## ▶️ How to Run

1. Install Java JDK.
2. Install MySQL.
3. Open the project in Eclipse.
4. Add MySQL Connector/J to the project build path.
5. Create the `student_db` database.
6. Configure the MySQL username and password.
7. Run `Main.java`.
8. Select an option from the menu.

## 🎯 Future Improvements

- GUI using Java Swing or JavaFX
- Login system
- Admin dashboard
- Export student records
- Search by name or course

## 👨‍💻 Author

Vickt2006
