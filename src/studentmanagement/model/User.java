package studentmanagement.model;

public class User {

    private int id;

    private String username;

    private String password;

    private String role;

    private int studentId;


    // ================= DEFAULT CONSTRUCTOR =================

    public User() {

    }


    // ================= PARAMETERIZED CONSTRUCTOR =================

    public User(int id, String username, String password, String role, int studentId) {

        this.id = id;

        this.username = username;

        this.password = password;

        this.role = role;

        this.studentId = studentId;
    }


    // ================= ID =================

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }


    // ================= USERNAME =================

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }


    // ================= PASSWORD =================

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


    // ================= ROLE =================

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }


    // ================= STUDENT ID =================

    public int getStudentId() {

        return studentId;
    }

    public void setStudentId(int studentId) {

        this.studentId = studentId;
    }
}