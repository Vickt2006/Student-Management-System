package studentmanagement.main;

import java.util.Scanner;

import studentmanagement.dao.StudentDAO;
import studentmanagement.model.Student;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new StudentDAO();

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("========================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

            // =====================================
            // 1. ADD STUDENT
            // =====================================
            case 1:

                sc.nextLine();

                // NAME VALIDATION
                String name;

                while (true) {

                    System.out.print("Enter Name: ");
                    name = sc.nextLine();

                    if (!name.trim().isEmpty()) {
                        break;
                    }

                    System.out.println("Name cannot be empty!");
                }

                // AGE VALIDATION
                int age;

                while (true) {

                    System.out.print("Enter Age: ");
                    age = sc.nextInt();

                    if (age >= 1 && age <= 100) {
                        break;
                    }

                    System.out.println("Invalid age! Enter age between 1 and 100.");
                }

                sc.nextLine();

                // COURSE VALIDATION
                String course;

                while (true) {

                    System.out.print("Enter Course: ");
                    course = sc.nextLine();

                    if (!course.trim().isEmpty()) {
                        break;
                    }

                    System.out.println("Course cannot be empty!");
                }

                // EMAIL VALIDATION
                String email;

                while (true) {

                    System.out.print("Enter Email: ");
                    email = sc.nextLine();

                    if (email.contains("@") && email.contains(".")) {
                        break;
                    }

                    System.out.println("Invalid email! Please enter a valid email.");
                }

                // PHONE VALIDATION
                String phone;

                while (true) {

                    System.out.print("Enter Phone (10 digits): ");
                    phone = sc.nextLine();

                    if (phone.matches("\\d{10}")) {
                        break;
                    }

                    System.out.println("Invalid phone number! Enter exactly 10 digits.");
                }

                Student student = new Student(
                        0,
                        name,
                        age,
                        course,
                        email,
                        phone
                );

                dao.addStudent(student);

                break;


            // =====================================
            // 2. VIEW ALL STUDENTS
            // =====================================
            case 2:

                dao.getAllStudents();

                break;


            // =====================================
            // 3. SEARCH STUDENT
            // =====================================
            case 3:

                System.out.print("Enter Student ID: ");
                int searchId = sc.nextInt();

                dao.getStudentById(searchId);

                break;


            // =====================================
            // 4. UPDATE STUDENT
            // =====================================
            case 4:

                System.out.print("Enter Student ID to update: ");
                int updateId = sc.nextInt();

                sc.nextLine();

                // NEW NAME
                String newName;

                while (true) {

                    System.out.print("Enter New Name: ");
                    newName = sc.nextLine();

                    if (!newName.trim().isEmpty()) {
                        break;
                    }

                    System.out.println("Name cannot be empty!");
                }

                // NEW AGE
                int newAge;

                while (true) {

                    System.out.print("Enter New Age: ");
                    newAge = sc.nextInt();

                    if (newAge >= 1 && newAge <= 100) {
                        break;
                    }

                    System.out.println("Invalid age! Enter age between 1 and 100.");
                }

                sc.nextLine();

                // NEW COURSE
                String newCourse;

                while (true) {

                    System.out.print("Enter New Course: ");
                    newCourse = sc.nextLine();

                    if (!newCourse.trim().isEmpty()) {
                        break;
                    }

                    System.out.println("Course cannot be empty!");
                }

                // NEW EMAIL
                String newEmail;

                while (true) {

                    System.out.print("Enter New Email: ");
                    newEmail = sc.nextLine();

                    if (newEmail.contains("@") && newEmail.contains(".")) {
                        break;
                    }

                    System.out.println("Invalid email!");
                }

                // NEW PHONE
                String newPhone;

                while (true) {

                    System.out.print("Enter New Phone (10 digits): ");
                    newPhone = sc.nextLine();

                    if (newPhone.matches("\\d{10}")) {
                        break;
                    }

                    System.out.println("Invalid phone number! Enter exactly 10 digits.");
                }

                Student updatedStudent = new Student(
                        updateId,
                        newName,
                        newAge,
                        newCourse,
                        newEmail,
                        newPhone
                );

                dao.updateStudent(updatedStudent);

                break;


            // =====================================
            // 5. DELETE STUDENT
            // =====================================
            case 5:

                System.out.print("Enter Student ID to delete: ");
                int deleteId = sc.nextInt();

                sc.nextLine();

                System.out.print("Are you sure you want to delete this student? (Y/N): ");
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {

                    dao.deleteStudent(deleteId);

                } else {

                    System.out.println("Delete operation cancelled.");

                }

                break;


            // =====================================
            // 6. EXIT
            // =====================================
            case 6:

                System.out.println("\nThank you for using Student Management System!");
                System.out.println("Goodbye!");

                break;


            // =====================================
            // INVALID CHOICE
            // =====================================
            default:

                System.out.println("Invalid choice! Please select 1 to 6.");

            }

        } while (choice != 6);

        sc.close();
    }
} 