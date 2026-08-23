package studentmanagement.model;

public class Marks {

    private int id;
    private int studentId;
    private String subject;
    private int marks;
    private int totalMarks;

    public Marks() {
    }

    public Marks(int id, int studentId, String subject, int marks, int totalMarks) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
        this.totalMarks = totalMarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}