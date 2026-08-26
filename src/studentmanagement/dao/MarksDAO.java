package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Marks;
import studentmanagement.util.DBConnection;

public class MarksDAO {

    // ================= ADD MARKS =================

    public void addMarks(Marks marks) {

        // Student ID validation
        if (marks.getStudentId() <= 0) {

            System.out.println("Please enter a valid Student ID!");
            return;
        }

        // Subject validation
        if (marks.getSubject() == null
                || marks.getSubject().trim().isEmpty()) {

            System.out.println("Subject cannot be empty!");
            return;
        }

        // Total marks validation
        if (marks.getTotalMarks() <= 0) {

            System.out.println("Total marks must be greater than 0!");
            return;
        }

        // Obtained marks validation
        if (marks.getMarks() < 0) {

            System.out.println("Marks cannot be negative!");
            return;
        }

        // Marks cannot be greater than total
        if (marks.getMarks() > marks.getTotalMarks()) {

            System.out.println(
                    "Obtained marks cannot be greater than total marks!"
            );

            return;
        }

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

            System.out.println("Error while adding marks.");
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

                int marks = rs.getInt("marks");
                int totalMarks = rs.getInt("total_marks");

                double percentage =
                        ((double) marks / totalMarks) * 100;

                System.out.println("Marks ID: "
                        + rs.getInt("id"));

                System.out.println("Student ID: "
                        + rs.getInt("student_id"));

                System.out.println("Subject: "
                        + rs.getString("subject"));

                System.out.println("Marks: "
                        + marks);

                System.out.println("Total Marks: "
                        + totalMarks);

                System.out.printf(
                        "Percentage: %.2f%%%n",
                        percentage
                );

                System.out.println("Grade: "
                        + calculateGrade(percentage));

                System.out.println("--------------------------------");
            }

            if (!found) {

                System.out.println("No marks records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while fetching marks.");
            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT MARKS =================

    public void viewStudentMarks(int studentId) {

        if (studentId <= 0) {

            System.out.println("Please enter a valid Student ID!");
            return;
        }

        String sql = "SELECT * FROM marks WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT MARKS ==========");

            boolean found = false;

            int totalObtained = 0;
            int totalPossible = 0;

            while (rs.next()) {

                found = true;

                int marks = rs.getInt("marks");

                int totalMarks = rs.getInt("total_marks");

                double percentage =
                        ((double) marks / totalMarks) * 100;

                System.out.println("Subject: "
                        + rs.getString("subject"));

                System.out.println("Marks: "
                        + marks
                        + " / "
                        + totalMarks);

                System.out.printf(
                        "Percentage: %.2f%%%n",
                        percentage
                );

                System.out.println("Grade: "
                        + calculateGrade(percentage));

                System.out.println("-----------------------------------");

                totalObtained += marks;
                totalPossible += totalMarks;
            }

            if (!found) {

                System.out.println("No marks records found.");

            } else {

                // Overall percentage

                double overallPercentage =
                        ((double) totalObtained
                        / totalPossible) * 100;

                System.out.println();
                System.out.println("========== OVERALL RESULT ==========");

                System.out.println("Total Obtained: "
                        + totalObtained);

                System.out.println("Total Possible: "
                        + totalPossible);

                System.out.printf(
                        "Overall Percentage: %.2f%%%n",
                        overallPercentage
                );

                System.out.println("Overall Grade: "
                        + calculateGrade(overallPercentage));

                System.out.println("====================================");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while fetching student marks.");
            e.printStackTrace();
        }
    }


    // ================= CALCULATE GRADE =================

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