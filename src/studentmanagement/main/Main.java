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

            if (user.getRole().equalsIgnoreCase("ADMIN")) {

                showAdminDashboard(sc);

            } else if (user.getRole().equalsIgnoreCase("STUDENT")) {

                showStudentDashboard(sc, user);

            } else {

                System.out.println("Unknown user role!");
            }

        } else {

            System.out.println();
            System.out.println("Invalid username or password!");
        }

        sc.close();
    }


    // =====================================================
    // SAFE INTEGER INPUT
    // =====================================================

    public static int getIntInput(
            Scanner sc,
            String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextInt()) {

                int value = sc.nextInt();
                sc.nextLine();

                return value;

            } else {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                sc.nextLine();
            }
        }
    }


    // =====================================================
    // SAFE DOUBLE INPUT
    // =====================================================

    public static double getDoubleInput(
            Scanner sc,
            String message) {

        while (true) {

            System.out.print(message);

            if (sc.hasNextDouble()) {

                double value = sc.nextDouble();
                sc.nextLine();

                return value;

            } else {

                System.out.println(
                        "Invalid input! Please enter a valid number."
                );

                sc.nextLine();
            }
        }
    }


    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    public static void showAdminDashboard(
            Scanner sc) {

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

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

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
                    System.out.println(
                            "Logged out successfully."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-6."
                    );
            }

        } while (choice != 6);
    }


    // =====================================================
    // STUDENT DASHBOARD
    // =====================================================

    public static void showStudentDashboard(
            Scanner sc,
            User user) {

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("            STUDENT DASHBOARD");
            System.out.println("========================================");

            System.out.println(
                    "Welcome, " + user.getUsername()
            );

            System.out.println(
                    "Student ID: " + user.getStudentId()
            );

            System.out.println("1. My Profile");
            System.out.println("2. My Attendance");
            System.out.println("3. My Marks");
            System.out.println("4. My Fees");
            System.out.println("5. Logout");

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    myProfile(user);
                    break;

                case 2:
                    myAttendance(user);
                    break;

                case 3:
                    myMarks(user);
                    break;

                case 4:
                    myFees(user);
                    break;

                case 5:
                    System.out.println(
                            "Logged out successfully."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-5."
                    );
            }

        } while (choice != 5);
    }


    // =====================================================
    // MY PROFILE
    // =====================================================

    public static void myProfile(User user) {

        System.out.println();
        System.out.println("========== MY PROFILE ==========");

        System.out.println(
                "User ID: " + user.getId()
        );

        System.out.println(
                "Username: " + user.getUsername()
        );

        System.out.println(
                "Role: " + user.getRole()
        );

        System.out.println(
                "Student ID: " + user.getStudentId()
        );

        StudentDAO studentDAO =
                new StudentDAO();

        Student student =
                studentDAO.getStudentById(
                        user.getStudentId()
                );

        if (student != null) {

            System.out.println();
            System.out.println(
                    "---------- STUDENT DETAILS ----------"
            );

            System.out.println(
                    "Name: " + student.getName()
            );

            System.out.println(
                    "Age: " + student.getAge()
            );

            System.out.println(
                    "Course: " + student.getCourse()
            );

            System.out.println(
                    "Email: " + student.getEmail()
            );

            System.out.println(
                    "Phone: " + student.getPhone()
            );

        } else {

            System.out.println();
            System.out.println(
                    "Student profile not found!"
            );
        }

        System.out.println(
                "================================"
        );
    }


    // =====================================================
    // MY ATTENDANCE
    // =====================================================

    public static void myAttendance(User user) {

        System.out.println();
        System.out.println(
                "========== MY ATTENDANCE =========="
        );

        int studentId = user.getStudentId();

        if (studentId <= 0) {

            System.out.println(
                    "No student profile linked to this account!"
            );

            return;
        }

        AttendanceDAO attendanceDAO =
                new AttendanceDAO();

        attendanceDAO.viewStudentAttendance(
                studentId
        );
    }


    // =====================================================
    // MY MARKS
    // =====================================================

    public static void myMarks(User user) {

        System.out.println();
        System.out.println(
                "========== MY MARKS =========="
        );

        int studentId = user.getStudentId();

        if (studentId <= 0) {

            System.out.println(
                    "No student profile linked to this account!"
            );

            return;
        }

        MarksDAO marksDAO =
                new MarksDAO();

        marksDAO.viewStudentMarks(
                studentId
        );
    }


    // =====================================================
    // MY FEES
    // =====================================================

    public static void myFees(User user) {

        System.out.println();
        System.out.println(
                "========== MY FEES =========="
        );

        int studentId = user.getStudentId();

        if (studentId <= 0) {

            System.out.println(
                    "No student profile linked to this account!"
            );

            return;
        }

        FeesDAO feesDAO =
                new FeesDAO();

        feesDAO.viewStudentFees(
                studentId
        );
    }


    // =====================================================
    // STUDENT MANAGEMENT
    // =====================================================

    public static void studentManagement(
            Scanner sc) {

        StudentDAO studentDAO =
                new StudentDAO();

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

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    addStudent(sc, studentDAO);
                    break;

                case 2:
                    studentDAO.getAllStudents();
                    break;

                case 3:

                    int searchId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID to search: "
                            );

                    studentDAO.searchStudent(searchId);

                    break;

                case 4:
                    updateStudent(sc, studentDAO);
                    break;

                case 5:

                    int deleteId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID to delete: "
                            );

                    studentDAO.deleteStudent(deleteId);

                    break;

                case 6:
                    System.out.println(
                            "Returning to Dashboard..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-6."
                    );
            }

        } while (choice != 6);
    }


    // =====================================================
    // ADD STUDENT
    // =====================================================

    public static void addStudent(
            Scanner sc,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println(
                "========== ADD STUDENT =========="
        );

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int age =
                getIntInput(
                        sc,
                        "Enter Age: "
                );

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        Student student =
                new Student();

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

    public static void updateStudent(
            Scanner sc,
            StudentDAO studentDAO) {

        System.out.println();
        System.out.println(
                "========== UPDATE STUDENT =========="
        );

        int id =
                getIntInput(
                        sc,
                        "Enter Student ID: "
                );

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        int age =
                getIntInput(
                        sc,
                        "Enter New Age: "
                );

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New Phone: ");
        String phone = sc.nextLine();

        Student student =
                new Student();

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

    public static void attendanceManagement(
            Scanner sc) {

        AttendanceDAO attendanceDAO =
                new AttendanceDAO();

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

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    markAttendance(sc, attendanceDAO);
                    break;

                case 2:
                    attendanceDAO.viewAllAttendance();
                    break;

                case 3:

                    int studentId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID: "
                            );

                    attendanceDAO.viewStudentAttendance(
                            studentId
                    );

                    break;

                case 4:
                    System.out.println(
                            "Returning to Dashboard..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-4."
                    );
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
        System.out.println(
                "========== MARK ATTENDANCE =========="
        );

        int studentId =
                getIntInput(
                        sc,
                        "Enter Student ID: "
                );

        System.out.print(
                "Enter Date (YYYY-MM-DD): "
        );

        String date = sc.nextLine();

        System.out.print(
                "Enter Status (Present/Absent): "
        );

        String status = sc.nextLine();

        Attendance attendance =
                new Attendance();

        attendance.setStudentId(studentId);
        attendance.setAttendanceDate(date);
        attendance.setStatus(status);

        attendanceDAO.markAttendance(
                attendance
        );
    }


    // =====================================================
    // MARKS MANAGEMENT
    // =====================================================

    public static void marksManagement(
            Scanner sc) {

        MarksDAO marksDAO =
                new MarksDAO();

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

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    addMarks(sc, marksDAO);
                    break;

                case 2:
                    marksDAO.viewAllMarks();
                    break;

                case 3:

                    int studentId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID: "
                            );

                    marksDAO.viewStudentMarks(
                            studentId
                    );

                    break;

                case 4:
                    System.out.println(
                            "Returning to Dashboard..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-4."
                    );
            }

        } while (choice != 4);
    }


    // =====================================================
    // ADD MARKS
    // =====================================================

    public static void addMarks(
            Scanner sc,
            MarksDAO marksDAO) {

        System.out.println();
        System.out.println(
                "========== ADD MARKS =========="
        );

        int studentId =
                getIntInput(
                        sc,
                        "Enter Student ID: "
                );

        System.out.print("Enter Subject: ");
        String subject = sc.nextLine();

        int marks =
                getIntInput(
                        sc,
                        "Enter Marks: "
                );

        int totalMarks =
                getIntInput(
                        sc,
                        "Enter Total Marks: "
                );

        Marks mark =
                new Marks();

        mark.setStudentId(studentId);
        mark.setSubject(subject);
        mark.setMarks(marks);
        mark.setTotalMarks(totalMarks);

        marksDAO.addMarks(mark);
    }


    // =====================================================
    // FEES MANAGEMENT
    // =====================================================

    public static void feesManagement(
            Scanner sc) {

        FeesDAO feesDAO =
                new FeesDAO();

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

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    addFee(sc, feesDAO);
                    break;

                case 2:
                    feesDAO.viewAllFees();
                    break;

                case 3:

                    int studentId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID: "
                            );

                    feesDAO.viewStudentFees(
                            studentId
                    );

                    break;

                case 4:
                    updateFeeStatus(sc, feesDAO);
                    break;

                case 5:
                    System.out.println(
                            "Returning to Dashboard..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1-5."
                    );
            }

        } while (choice != 5);
    }


    // =====================================================
    // ADD FEE
    // =====================================================

    public static void addFee(
            Scanner sc,
            FeesDAO feesDAO) {

        System.out.println();
        System.out.println(
                "========== ADD FEE =========="
        );

        int studentId =
                getIntInput(
                        sc,
                        "Enter Student ID: "
                );

        double amount =
                getDoubleInput(
                        sc,
                        "Enter Amount: "
                );

        System.out.print(
                "Enter Payment Date (YYYY-MM-DD): "
        );

        String paymentDate = sc.nextLine();

        System.out.print(
                "Enter Payment Status (Paid/Pending): "
        );

        String paymentStatus = sc.nextLine();

        System.out.print(
                "Enter Payment Method (Cash/UPI/Card): "
        );

        String paymentMethod = sc.nextLine();

        Fees fee =
                new Fees();

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
        System.out.println(
                "========== UPDATE FEE STATUS =========="
        );

        int feeId =
                getIntInput(
                        sc,
                        "Enter Fee ID: "
                );

        System.out.print(
                "Enter New Status (Paid/Pending): "
        );

        String status = sc.nextLine();

        feesDAO.updateFeeStatus(
                feeId,
                status
        );
    }


    // =====================================================
    // REPORTS MANAGEMENT
    // =====================================================

    public static void reportsManagement(
            Scanner sc) {

        ReportDAO reportDAO =
                new ReportDAO();

        int choice;

        do {

            System.out.println();
            System.out.println("========================================");
            System.out.println("              REPORTS");
            System.out.println("========================================");

            System.out.println(
                    "1. Student Full Report"
            );

            System.out.println(
                    "2. Back to Dashboard"
            );

            choice = getIntInput(
                    sc,
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:

                    int studentId =
                            getIntInput(
                                    sc,
                                    "Enter Student ID: "
                            );

                    reportDAO.studentFullReport(
                            studentId
                    );

                    break;

                case 2:

                    System.out.println(
                            "Returning to Dashboard..."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice! Please select 1-2."
                    );
            }

        } while (choice != 2);
    }
}