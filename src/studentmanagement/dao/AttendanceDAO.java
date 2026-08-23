package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Attendance;
import studentmanagement.util.DBConnection;

public class AttendanceDAO {

    // ================= MARK ATTENDANCE =================

    public void markAttendance(Attendance attendance) {

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

            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT ATTENDANCE =================

    public void viewStudentAttendance(int studentId) {

        String sql = "SELECT * FROM attendance WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT ATTENDANCE ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

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

            e.printStackTrace();
        }
    }
}