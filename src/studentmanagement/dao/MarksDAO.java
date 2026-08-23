package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Marks;
import studentmanagement.util.DBConnection;

public class MarksDAO {

    // ================= ADD MARKS =================

    public void addMarks(Marks marks) {

        String sql = "INSERT INTO marks "
                   + "(student_id, subject, marks, total_marks) "
                   + "VALUES (?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, marks.getStudentId());
            ps.setString(2, marks.getSubject());
            ps.setInt(3, marks.getMarks());
            ps.setInt(4, marks.getTotalMarks());

            ps.executeUpdate();

            System.out.println("Marks added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ================= VIEW ALL MARKS =================

    public void viewAllMarks() {

        String sql = "SELECT * FROM marks";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== ALL MARKS ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Marks ID: "
                        + rs.getInt("id"));

                System.out.println("Student ID: "
                        + rs.getInt("student_id"));

                System.out.println("Subject: "
                        + rs.getString("subject"));

                System.out.println("Marks: "
                        + rs.getInt("marks"));

                System.out.println("Total Marks: "
                        + rs.getInt("total_marks"));

                System.out.println("--------------------------------");
            }

            if (!found) {

                System.out.println("No marks records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT MARKS =================

    public void viewStudentMarks(int studentId) {

        String sql = "SELECT * FROM marks WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT MARKS ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Subject: "
                        + rs.getString("subject"));

                System.out.println("Marks: "
                        + rs.getInt("marks")
                        + " / "
                        + rs.getInt("total_marks"));

                double percentage =
                        (rs.getDouble("marks")
                        / rs.getDouble("total_marks")) * 100;

                System.out.println("Percentage: "
                        + percentage + "%");

                System.out.println("-----------------------------------");
            }

            if (!found) {

                System.out.println("No marks records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}