public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester){
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public String getStudentName(){
        return student.getName();
    }

    public String getStudentId(){
        return student.getId();
    }

    public String getStudentBirthday(){return student.getBirthdate();}

    public String getCourseName(){
        return course.getName();
    }

    public String getCourseId(){
        return course.getId();
    }

    public int getCourseCredit(){
        return course.getCredit();
    }

    public String getSemester(){
        return semester;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {

        return
                getStudentId() + " " + getStudentName() + " " + getStudentBirthday() + " " +
                getCourseId() + " " + getCourseName() + " " + getCourseCredit() + " " + semester;
    }
}
