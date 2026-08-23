package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Student;
import studentmanagement.util.DBConnection;

public class StudentDAO {

    // ==========================================
    // 1. ADD STUDENT
    // ==========================================
    public void addStudent(Student student) {

        String sql = "INSERT INTO students (name, age, course, email, phone) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());

            ps.executeUpdate();

            System.out.println("Student added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ==========================================
    // 2. VIEW ALL STUDENTS
    // ==========================================
    public void getAllStudents() {

        String sql = "SELECT * FROM students";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("-------------------------");

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Course: " + rs.getString("course"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
            }

            System.out.println("-------------------------");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ==========================================
    // 3. SEARCH STUDENT BY ID
    // ==========================================
    public void getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Student Found!");
                System.out.println("-------------------------");

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Course: " + rs.getString("course"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));

                System.out.println("-------------------------");

            } else {

                System.out.println("Student not found!");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ==========================================
    // 4. UPDATE STUDENT
    // ==========================================
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET name = ?, age = ?, course = ?, email = ?, phone = ? WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setInt(6, student.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student updated successfully!");

            } else {

                System.out.println("Student not found!");

            }

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    // ==========================================
    // 5. DELETE STUDENT
    // ==========================================
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student deleted successfully!");

            } else {

                System.out.println("Student not found!");

            }

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}