package studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import studentmanagement.model.Fees;
import studentmanagement.util.DBConnection;

public class FeesDAO {

    // ================= ADD FEE =================

    public void addFee(Fees fee) {

        // Student ID validation
        if (fee.getStudentId() <= 0) {

            System.out.println("Please enter a valid Student ID!");
            return;
        }

        // Amount validation
        if (fee.getAmount() <= 0) {

            System.out.println("Fee amount must be greater than 0!");
            return;
        }

        // Payment date validation
        if (fee.getPaymentDate() == null
                || fee.getPaymentDate().trim().isEmpty()) {

            System.out.println("Payment date cannot be empty!");
            return;
        }

        // Payment status validation
        if (fee.getPaymentStatus() == null
                || (!fee.getPaymentStatus().equalsIgnoreCase("Paid")
                && !fee.getPaymentStatus().equalsIgnoreCase("Pending"))) {

            System.out.println(
                    "Payment status must be Paid or Pending!"
            );

            return;
        }

        // Payment method validation
        if (fee.getPaymentMethod() == null
                || fee.getPaymentMethod().trim().isEmpty()) {

            System.out.println("Payment method cannot be empty!");
            return;
        }

        String method = fee.getPaymentMethod();

        if (!method.equalsIgnoreCase("Cash")
                && !method.equalsIgnoreCase("UPI")
                && !method.equalsIgnoreCase("Card")) {

            System.out.println(
                    "Payment method must be Cash, UPI, or Card!"
            );

            return;
        }

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

            System.out.println("Error while adding fee.");
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

            System.out.println("Error while fetching fees.");
            e.printStackTrace();
        }
    }


    // ================= VIEW STUDENT FEES =================

    public void viewStudentFees(int studentId) {

        if (studentId <= 0) {

            System.out.println("Please enter a valid Student ID!");
            return;
        }

        String sql = "SELECT * FROM fees WHERE student_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            System.out.println();
            System.out.println("========== STUDENT FEES ==========");

            boolean found = false;

            double totalAmount = 0;
            double paidAmount = 0;
            double pendingAmount = 0;

            while (rs.next()) {

                found = true;

                double amount = rs.getDouble("amount");

                String status = rs.getString("payment_status");

                System.out.println("Fee ID: "
                        + rs.getInt("id"));

                System.out.println("Amount: ₹"
                        + amount);

                System.out.println("Payment Date: "
                        + rs.getString("payment_date"));

                System.out.println("Payment Status: "
                        + status);

                System.out.println("Payment Method: "
                        + rs.getString("payment_method"));

                System.out.println("-----------------------------------");

                totalAmount += amount;

                if (status.equalsIgnoreCase("Paid")) {

                    paidAmount += amount;

                } else if (status.equalsIgnoreCase("Pending")) {

                    pendingAmount += amount;
                }
            }

            if (!found) {

                System.out.println("No fee records found.");

            } else {

                System.out.println();
                System.out.println("========== FEE SUMMARY ==========");

                System.out.println("Total Fee: ₹"
                        + totalAmount);

                System.out.println("Paid Amount: ₹"
                        + paidAmount);

                System.out.println("Pending Amount: ₹"
                        + pendingAmount);

                System.out.println("=================================");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while fetching student fees.");
            e.printStackTrace();
        }
    }


    // ================= UPDATE FEE STATUS =================

    public void updateFeeStatus(int feeId, String status) {

        if (feeId <= 0) {

            System.out.println("Please enter a valid Fee ID!");
            return;
        }

        if (status == null
                || (!status.equalsIgnoreCase("Paid")
                && !status.equalsIgnoreCase("Pending"))) {

            System.out.println(
                    "Payment status must be Paid or Pending!"
            );

            return;
        }

        String sql = "UPDATE fees SET payment_status = ? WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, feeId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Fee status updated successfully!"
                );

            } else {

                System.out.println("Fee record not found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            System.out.println("Error while updating fee status.");
            e.printStackTrace();
        }
    }
}