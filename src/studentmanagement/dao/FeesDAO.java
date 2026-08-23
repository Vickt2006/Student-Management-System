package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Fees;
import studentmanagement.util.DBConnection;

public class FeesDAO {

    // ================= ADD FEE =================

    public void addFee(Fees fee) {

        String sql = "INSERT INTO fees "
                   + "(student_id, amount, payment_date, payment_status, payment_method) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, fee.getStudentId());
            ps.setDouble(2, fee.getAmount());
            ps.setString(3, fee.getPaymentDate());
            ps.setString(4, fee.getPaymentStatus());
            ps.setString(5, fee.getPaymentMethod());

            ps.executeUpdate();

            System.out.println("Fee added successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ================= VIEW ALL FEES =================

    public void viewAllFees() {

        String sql = "SELECT * FROM fees";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== ALL FEES ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Fee ID: "
                        + rs.getInt("id"));

                System.out.println("Student ID: "
                        + rs.getInt("student_id"));

                System.out.println("Amount: ₹"
                        + rs.getDouble("amount"));

                System.out.println("Payment Date: "
                        + rs.getString("payment_date"));

                System.out.println("Payment Status: "
                        + rs.getString("payment_status"));

                System.out.println("Payment Method: "
                        + rs.getString("payment_method"));

                System.out.println("--------------------------------");
            }

            if (!found) {

                System.out.println("No fee records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT FEES =================

    public void viewStudentFees(int studentId) {

        String sql = "SELECT * FROM fees WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT FEES ==========");

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("Fee ID: "
                        + rs.getInt("id"));

                System.out.println("Amount: ₹"
                        + rs.getDouble("amount"));

                System.out.println("Payment Date: "
                        + rs.getString("payment_date"));

                System.out.println("Payment Status: "
                        + rs.getString("payment_status"));

                System.out.println("Payment Method: "
                        + rs.getString("payment_method"));

                System.out.println("-----------------------------------");
            }

            if (!found) {

                System.out.println("No fee records found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ================= UPDATE FEE STATUS =================

    public void updateFeeStatus(int feeId, String status) {

        String sql = "UPDATE fees SET payment_status = ? WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, feeId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Fee status updated successfully!");

            } else {

                System.out.println("Fee record not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}