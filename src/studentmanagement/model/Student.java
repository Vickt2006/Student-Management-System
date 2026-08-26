package studentmanagement.model;

public class Student {

    private int id;
    private String name;
    private int age;
    private String course;
    private String email;
    private String phone;

    // ================= EMPTY CONSTRUCTOR =================

    public Student() {

    }

    // ================= CONSTRUCTOR =================

    public Student(
            int id,
            String name,
            int age,
            String course,
            String email,
            String phone) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
        this.phone = phone;
    }

    // ================= GET ID =================

    public int getId() {

        return id;
    }

    // ================= SET ID =================

    public void setId(int id) {

        this.id = id;
    }

    // ================= GET NAME =================

    public String getName() {

        return name;
    }

    // ================= SET NAME =================

    public void setName(String name) {

        this.name = name;
    }

    // ================= GET AGE =================

    public int getAge() {

        return age;
    }

    // ================= SET AGE =================

    public void setAge(int age) {

        this.age = age;
    }

    // ================= GET COURSE =================

    public String getCourse() {

        return course;
    }

    // ================= SET COURSE =================

    public void setCourse(String course) {

        this.course = course;
    }

    // ================= GET EMAIL =================

    public String getEmail() {

        return email;
    }

    // ================= SET EMAIL =================

    public void setEmail(String email) {

        this.email = email;
    }

    // ================= GET PHONE =================

    public String getPhone() {

        return phone;
    }

    // ================= SET PHONE =================

    public void setPhone(String phone) {

        this.phone = phone;
    }
}