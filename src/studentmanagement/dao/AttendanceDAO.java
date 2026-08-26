package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Attendance;
import studentmanagement.util.DBConnection;

public class AttendanceDAO {

    // ================= MARK ATTENDANCE =================

    public void markAttendance(Attendance attendance) {

        if (attendance.getStudentId() <= 0) {
            System.out.println("Please enter a valid Student ID!");
            return;
        }

        if (attendance.getAttendanceDate() == null
                || attendance.getAttendanceDate().trim().isEmpty()) {

            System.out.println("Attendance date cannot be empty!");
            return;
        }

        if (attendance.getStatus() == null
                || (!attendance.getStatus().equalsIgnoreCase("Present")
                && !attendance.getStatus().equalsIgnoreCase("Absent"))) {

            System.out.println("Status must be Present or Absent!");
            return;
        }

        String sql = "INSERT INTO attendance "
                   + "(student_id, attendance_date, status) "
                   + "VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, attendance.getStudentId());
            ps.setString(2, attendance.getAttendanceDate());
            ps.setString(3, attendance.getStatus());

            ps.executeUpdate();

            System.out.println("Attendance marked successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while marking attendance.");
            e.printStackTrace();
        }
    }


    // ================= VIEW ALL ATTENDANCE =================

    public void viewAllAttendance() {

        String sql = "SELECT * FROM attendance";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== ATTENDANCE RECORDS ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Attendance ID: "
                        + rs.getInt("id"));

                System.out.println("Student ID: "
                        + rs.getInt("student_id"));

                System.out.println("Date: "
                        + rs.getString("attendance_date"));

                System.out.println("Status: "
                        + rs.getString("status"));

                System.out.println("----------------------------------------");
            }

            if (!found) {

                System.out.println("No attendance records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while fetching attendance.");
            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT ATTENDANCE =================

    public void viewStudentAttendance(int studentId) {

        if (studentId <= 0) {

            System.out.println("Please enter a valid Student ID!");
            return;
        }

        String sql = "SELECT * FROM attendance WHERE student_id = ? "
                   + "ORDER BY attendance_date";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT ATTENDANCE ==========");

            boolean found = false;

            int totalDays = 0;
            int presentDays = 0;
            int absentDays = 0;

            while (rs.next()) {

                found = true;

                String status = rs.getString("status");

                System.out.println("Date: "
                        + rs.getString("attendance_date"));

                System.out.println("Status: "
                        + status);

                System.out.println("----------------------------------------");

                totalDays++;

                if (status.equalsIgnoreCase("Present")) {

                    presentDays++;

                } else if (status.equalsIgnoreCase("Absent")) {

                    absentDays++;
                }
            }

            if (!found) {

                System.out.println("No attendance records found.");

            } else {

                double percentage =
                        ((double) presentDays / totalDays) * 100;

                System.out.println();
                System.out.println("========== ATTENDANCE SUMMARY ==========");

                System.out.println("Total Days: " + totalDays);

                System.out.println("Present: " + presentDays);

                System.out.println("Absent: " + absentDays);

                System.out.printf(
                        "Attendance Percentage: %.2f%%%n",
                        percentage
                );

                System.out.println("=========================================");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while fetching student attendance.");
            e.printStackTrace();
        }
    }
}