package studentmanagement.main;

import java.util.Scanner;

import studentmanagement.dao.AttendanceDAO;
import studentmanagement.dao.FeesDAO;
import studentmanagement.dao.MarksDAO;
import studentmanagement.dao.ReportDAO;
import studentmanagement.dao.StudentDAO;
import studentmanagement.dao.UserDAO;

import studentmanagement.model.Attendance;
import studentmanagement.model.Fees;
import studentmanagement.model.Marks;
import studentmanagement.model.Student;
import studentmanagement.model.User;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.println();
        System.out.println("             LOGIN");
        System.out.println("========================================");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        UserDAO userDAO = new UserDAO();

        User user = userDAO.login(username, password);

        if (user != null) {

            System.out.println();
            System.out.println("Login Successful!");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("Role: " + user.getRole());

            showDashboard(sc);

        } else {

            System.out.println();
            System.out.println("Invalid username or password!");
        }

        sc.close();
    }


    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    public static void showDashboard(Scanner sc) {

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("             ADMIN DASHBOARD");
            System.out.println("========================================");

            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Marks Management");
            System.out.println("4. Fees Management");
            System.out.println("5. Reports");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    studentManagement(sc);
                    break;

                case 2:
                    attendanceManagement(sc);
                    break;

                case 3:
                    marksManagement(sc);
                    break;

                case 4:
                    feesManagement(sc);
                    break;

                case 5:
                    reportsManagement(sc);
                    break;

                case 6:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    // =====================================================
    // STUDENT MANAGEMENT
    // =====================================================

    public static void studentManagement(Scanner sc) {

        StudentDAO studentDAO = new StudentDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("        STUDENT MANAGEMENT");
            System.out.println("========================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Dashboard");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent(sc, studentDAO);
                    break;

                case 2:
                    studentDAO.getAllStudents();
                    break;

                case 3:

                    System.out.print("Enter Student ID to search: ");

                    int searchId = sc.nextInt();

                    studentDAO.searchStudent(searchId);

                    break;

                case 4:
                    updateStudent(sc, studentDAO);
                    break;

                case 5:

                    System.out.print("Enter Student ID to delete: ");

                    int deleteId = sc.nextInt();

                    studentDAO.deleteStudent(deleteId);

                    break;

                case 6:
                    System.out.println("Returning to Dashboard...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }


    // =====================================================
    // ADD STUDENT
    // =====================================================

    public static void addStudent(Scanner sc, StudentDAO studentDAO) {

        System.out.println();
        System.out.println("========== ADD STUDENT ==========");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        Student student = new Student();

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setEmail(email);
        student.setPhone(phone);

        studentDAO.addStudent(student);
    }


    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    public static void updateStudent(Scanner sc, StudentDAO studentDAO) {

        System.out.println();
        System.out.println("========== UPDATE STUDENT ==========");

        System.out.print("Enter Student ID: ");

        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New Phone: ");
        String phone = sc.nextLine();

        Student student = new Student();

        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setEmail(email);
        student.setPhone(phone);

        studentDAO.updateStudent(student);
    }


    // =====================================================
    // ATTENDANCE MANAGEMENT
    // =====================================================

    public static void attendanceManagement(Scanner sc) {

        AttendanceDAO attendanceDAO = new AttendanceDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("       ATTENDANCE MANAGEMENT");
            System.out.println("========================================");

            System.out.println("1. Mark Attendance");
            System.out.println("2. View All Attendance");
            System.out.println("3. View Student Attendance");
            System.out.println("4. Back to Dashboard");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    markAttendance(sc, attendanceDAO);
                    break;

                case 2:
                    attendanceDAO.viewAllAttendance();
                    break;

                case 3:

                    System.out.print("Enter Student ID: ");

                    int studentId = sc.nextInt();

                    attendanceDAO.viewStudentAttendance(studentId);

                    break;

                case 4:
                    System.out.println("Returning to Dashboard...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }


    // =====================================================
    // MARK ATTENDANCE
    // =====================================================

    public static void markAttendance(
            Scanner sc,
            AttendanceDAO attendanceDAO) {

        System.out.println();
        System.out.println("========== MARK ATTENDANCE ==========");

        System.out.print("Enter Student ID: ");

        int studentId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");

        String date = sc.nextLine();

        System.out.print("Enter Status (Present/Absent): ");

        String status = sc.nextLine();

        Attendance attendance = new Attendance();

        attendance.setStudentId(studentId);
        attendance.setAttendanceDate(date);
        attendance.setStatus(status);

        attendanceDAO.markAttendance(attendance);
    }


    // =====================================================
    // MARKS MANAGEMENT
    // =====================================================

    public static void marksManagement(Scanner sc) {

        MarksDAO marksDAO = new MarksDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("          MARKS MANAGEMENT");
            System.out.println("========================================");

            System.out.println("1. Add Marks");
            System.out.println("2. View All Marks");
            System.out.println("3. View Student Marks");
            System.out.println("4. Back to Dashboard");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addMarks(sc, marksDAO);
                    break;

                case 2:
                    marksDAO.viewAllMarks();
                    break;

                case 3:

                    System.out.print("Enter Student ID: ");

                    int studentId = sc.nextInt();

                    marksDAO.viewStudentMarks(studentId);

                    break;

                case 4:
                    System.out.println("Returning to Dashboard...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);
    }


    // =====================================================
    // ADD MARKS
    // =====================================================

    public static void addMarks(Scanner sc, MarksDAO marksDAO) {

        System.out.println();
        System.out.println("========== ADD MARKS ==========");

        System.out.print("Enter Student ID: ");

        int studentId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Subject: ");

        String subject = sc.nextLine();

        System.out.print("Enter Marks: ");

        int marks = sc.nextInt();

        System.out.print("Enter Total Marks: ");

        int totalMarks = sc.nextInt();

        Marks mark = new Marks();

        mark.setStudentId(studentId);
        mark.setSubject(subject);
        mark.setMarks(marks);
        mark.setTotalMarks(totalMarks);

        marksDAO.addMarks(mark);
    }


    // =====================================================
    // FEES MANAGEMENT
    // =====================================================

    public static void feesManagement(Scanner sc) {

        FeesDAO feesDAO = new FeesDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("           FEES MANAGEMENT");
            System.out.println("========================================");

            System.out.println("1. Add Fee");
            System.out.println("2. View All Fees");
            System.out.println("3. View Student Fees");
            System.out.println("4. Update Fee Status");
            System.out.println("5. Back to Dashboard");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addFee(sc, feesDAO);
                    break;

                case 2:
                    feesDAO.viewAllFees();
                    break;

                case 3:

                    System.out.print("Enter Student ID: ");

                    int studentId = sc.nextInt();

                    feesDAO.viewStudentFees(studentId);

                    break;

                case 4:
                    updateFeeStatus(sc, feesDAO);
                    break;

                case 5:
                    System.out.println("Returning to Dashboard...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }


    // =====================================================
    // ADD FEE
    // =====================================================

    public static void addFee(Scanner sc, FeesDAO feesDAO) {

        System.out.println();
        System.out.println("========== ADD FEE ==========");

        System.out.print("Enter Student ID: ");

        int studentId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Amount: ");

        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter Payment Date (YYYY-MM-DD): ");

        String paymentDate = sc.nextLine();

        System.out.print("Enter Payment Status (Paid/Pending): ");

        String paymentStatus = sc.nextLine();

        System.out.print("Enter Payment Method (Cash/UPI/Card): ");

        String paymentMethod = sc.nextLine();

        Fees fee = new Fees();

        fee.setStudentId(studentId);
        fee.setAmount(amount);
        fee.setPaymentDate(paymentDate);
        fee.setPaymentStatus(paymentStatus);
        fee.setPaymentMethod(paymentMethod);

        feesDAO.addFee(fee);
    }


    // =====================================================
    // UPDATE FEE STATUS
    // =====================================================

    public static void updateFeeStatus(
            Scanner sc,
            FeesDAO feesDAO) {

        System.out.println();
        System.out.println("========== UPDATE FEE STATUS ==========");

        System.out.print("Enter Fee ID: ");

        int feeId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Status (Paid/Pending): ");

        String status = sc.nextLine();

        feesDAO.updateFeeStatus(feeId, status);
    }


    // =====================================================
    // REPORTS MANAGEMENT
    // =====================================================

    public static void reportsManagement(Scanner sc) {

        ReportDAO reportDAO = new ReportDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("              REPORTS");
            System.out.println("========================================");

            System.out.println("1. Student Full Report");
            System.out.println("2. Back to Dashboard");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");

                    int studentId = sc.nextInt();

                    reportDAO.studentFullReport(studentId);

                    break;

                case 2:

                    System.out.println("Returning to Dashboard...");

                    break;

                default:

                    System.out.println("Invalid choice!");
            }

        } while (choice != 2);
    }
}