package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.util.DBConnection;

public class ReportDAO {

    // ================= STUDENT FULL REPORT =================

    public void studentFullReport(int studentId) {

        String studentSql =
                "SELECT * FROM students WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement studentPs =
                    con.prepareStatement(studentSql);

            studentPs.setInt(1, studentId);

            ResultSet studentRs =
                    studentPs.executeQuery();

            if (!studentRs.next()) {

                System.out.println("Student not found!");

                studentRs.close();
                studentPs.close();
                con.close();

                return;
            }

            System.out.println();
            System.out.println("========================================");
            System.out.println("          STUDENT FULL REPORT");
            System.out.println("========================================");

            System.out.println("Student ID: "
                    + studentRs.getInt("id"));

            System.out.println("Name: "
                    + studentRs.getString("name"));

            System.out.println("Age: "
                    + studentRs.getInt("age"));

            System.out.println("Course: "
                    + studentRs.getString("course"));

            System.out.println("Email: "
                    + studentRs.getString("email"));

            System.out.println("Phone: "
                    + studentRs.getString("phone"));


            // ================= MARKS =================

            System.out.println();
            System.out.println("------------- MARKS ----------------");

            String marksSql =
                    "SELECT subject, marks, total_marks "
                    + "FROM marks WHERE student_id = ?";

            PreparedStatement marksPs =
                    con.prepareStatement(marksSql);

            marksPs.setInt(1, studentId);

            ResultSet marksRs =
                    marksPs.executeQuery();

            boolean marksFound = false;

            while (marksRs.next()) {

                marksFound = true;

                int marks =
                        marksRs.getInt("marks");

                int total =
                        marksRs.getInt("total_marks");

                double percentage =
                        ((double) marks / total) * 100;

                System.out.println(
                        marksRs.getString("subject")
                        + " : "
                        + marks
                        + " / "
                        + total
                        + " | "
                        + percentage
                        + "%"
                );
            }

            if (!marksFound) {
                System.out.println("No marks records found.");
            }


            // ================= ATTENDANCE =================

            System.out.println();
            System.out.println("---------- ATTENDANCE ---------------");

            String attendanceSql =
                    "SELECT attendance_date, status "
                    + "FROM attendance WHERE student_id = ?";

            PreparedStatement attendancePs =
                    con.prepareStatement(attendanceSql);

            attendancePs.setInt(1, studentId);

            ResultSet attendanceRs =
                    attendancePs.executeQuery();

            boolean attendanceFound = false;

            while (attendanceRs.next()) {

                attendanceFound = true;

                System.out.println(
                        attendanceRs.getString("attendance_date")
                        + " : "
                        + attendanceRs.getString("status")
                );
            }

            if (!attendanceFound) {
                System.out.println("No attendance records found.");
            }


            // ================= FEES =================

            System.out.println();
            System.out.println("-------------- FEES -----------------");

            String feesSql =
                    "SELECT amount, payment_date, "
                    + "payment_status, payment_method "
                    + "FROM fees WHERE student_id = ?";

            PreparedStatement feesPs =
                    con.prepareStatement(feesSql);

            feesPs.setInt(1, studentId);

            ResultSet feesRs =
                    feesPs.executeQuery();

            boolean feesFound = false;

            while (feesRs.next()) {

                feesFound = true;

                System.out.println(
                        "Amount: ₹"
                        + feesRs.getDouble("amount")
                );

                System.out.println(
                        "Date: "
                        + feesRs.getString("payment_date")
                );

                System.out.println(
                        "Status: "
                        + feesRs.getString("payment_status")
                );

                System.out.println(
                        "Method: "
                        + feesRs.getString("payment_method")
                );

                System.out.println("--------------------------------------");
            }

            if (!feesFound) {
                System.out.println("No fee records found.");
            }


            System.out.println();
            System.out.println("========================================");
            System.out.println("          END OF REPORT");
            System.out.println("========================================");


            // Close resources

            studentRs.close();
            studentPs.close();

            marksRs.close();
            marksPs.close();

            attendanceRs.close();
            attendancePs.close();

            feesRs.close();
            feesPs.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}