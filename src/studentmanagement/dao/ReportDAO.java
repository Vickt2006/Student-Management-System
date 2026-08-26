package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.util.DBConnection;

public class ReportDAO {

    // ================= STUDENT FULL REPORT =================

    public void studentFullReport(int studentId) {

        if (studentId <= 0) {
            System.out.println("Please enter a valid Student ID!");
            return;
        }

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

            // =================================================
            // STUDENT INFORMATION
            // =================================================

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


            // =================================================
            // MARKS
            // =================================================

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

            int totalObtained = 0;
            int totalPossible = 0;

            while (marksRs.next()) {

                marksFound = true;

                String subject =
                        marksRs.getString("subject");

                int marks =
                        marksRs.getInt("marks");

                int total =
                        marksRs.getInt("total_marks");

                double percentage =
                        ((double) marks / total) * 100;

                System.out.printf(
                        "%-15s : %d / %d | %.2f%% | Grade: %s%n",
                        subject,
                        marks,
                        total,
                        percentage,
                        calculateGrade(percentage)
                );

                totalObtained += marks;
                totalPossible += total;
            }

            if (!marksFound) {

                System.out.println("No marks records found.");

            } else {

                double overallPercentage =
                        ((double) totalObtained
                        / totalPossible) * 100;

                System.out.println("----------------------------------------");

                System.out.println("Total Obtained: "
                        + totalObtained);

                System.out.println("Total Possible: "
                        + totalPossible);

                System.out.printf(
                        "Overall Percentage: %.2f%%%n",
                        overallPercentage
                );

                System.out.println(
                        "Overall Grade: "
                        + calculateGrade(overallPercentage)
                );
            }


            // =================================================
            // ATTENDANCE
            // =================================================

            System.out.println();
            System.out.println("---------- ATTENDANCE ---------------");

            String attendanceSql =
                    "SELECT attendance_date, status "
                    + "FROM attendance "
                    + "WHERE student_id = ? "
                    + "ORDER BY attendance_date";

            PreparedStatement attendancePs =
                    con.prepareStatement(attendanceSql);

            attendancePs.setInt(1, studentId);

            ResultSet attendanceRs =
                    attendancePs.executeQuery();

            boolean attendanceFound = false;

            int totalDays = 0;
            int presentDays = 0;
            int absentDays = 0;

            while (attendanceRs.next()) {

                attendanceFound = true;

                String date =
                        attendanceRs.getString("attendance_date");

                String status =
                        attendanceRs.getString("status");

                System.out.println(
                        date + " : " + status
                );

                totalDays++;

                if (status.equalsIgnoreCase("Present")) {

                    presentDays++;

                } else if (status.equalsIgnoreCase("Absent")) {

                    absentDays++;
                }
            }

            if (!attendanceFound) {

                System.out.println("No attendance records found.");

            } else {

                double attendancePercentage =
                        ((double) presentDays / totalDays) * 100;

                System.out.println("----------------------------------------");

                System.out.println("Total Days: "
                        + totalDays);

                System.out.println("Present: "
                        + presentDays);

                System.out.println("Absent: "
                        + absentDays);

                System.out.printf(
                        "Attendance Percentage: %.2f%%%n",
                        attendancePercentage
                );
            }


            // =================================================
            // FEES
            // =================================================

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

            double totalFee = 0;
            double paidFee = 0;
            double pendingFee = 0;

            while (feesRs.next()) {

                feesFound = true;

                double amount =
                        feesRs.getDouble("amount");

                String status =
                        feesRs.getString("payment_status");

                System.out.println(
                        "Amount: ₹" + amount
                );

                System.out.println(
                        "Date: "
                        + feesRs.getString("payment_date")
                );

                System.out.println(
                        "Status: " + status
                );

                System.out.println(
                        "Method: "
                        + feesRs.getString("payment_method")
                );

                System.out.println("--------------------------------------");

                totalFee += amount;

                if (status.equalsIgnoreCase("Paid")) {

                    paidFee += amount;

                } else if (status.equalsIgnoreCase("Pending")) {

                    pendingFee += amount;
                }
            }

            if (!feesFound) {

                System.out.println("No fee records found.");

            } else {

                System.out.println();
                System.out.println("========== FEE SUMMARY ==========");

                System.out.println(
                        "Total Fee: ₹" + totalFee
                );

                System.out.println(
                        "Paid Amount: ₹" + paidFee
                );

                System.out.println(
                        "Pending Amount: ₹" + pendingFee
                );

                System.out.println("=================================");
            }


            // =================================================
            // END REPORT
            // =================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("          END OF REPORT");
            System.out.println("========================================");


            // =================================================
            // CLOSE RESOURCES
            // =================================================

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

            System.out.println("Error while generating report.");
            e.printStackTrace();
        }
    }


    // =====================================================
    // CALCULATE GRADE
    // =====================================================

    public String calculateGrade(double percentage) {

        if (percentage >= 90) {

            return "A+";

        } else if (percentage >= 80) {

            return "A";

        } else if (percentage >= 70) {

            return "B";

        } else if (percentage >= 60) {

            return "C";

        } else if (percentage >= 50) {

            return "D";

        } else {

            return "F";
        }
    }
}