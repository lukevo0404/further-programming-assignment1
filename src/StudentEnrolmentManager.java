import java.util.ArrayList;
import java.util.Objects;

public interface StudentEnrolmentManager {

    ArrayList<StudentEnrolment> enrolments = new ArrayList<>();

     static void add(Student student, Course course, String semester) {
        StudentEnrolment newEnrolment = new StudentEnrolment(student, course, semester);

        enrolments.add(newEnrolment);
        System.out.println(
                newEnrolment.getStudentName() + "(" + newEnrolment.getStudentId() + ") is successfully enrolled in "
                        + newEnrolment.getCourseName() + "(" + newEnrolment.getCourseId() + ") for semester "
                        + newEnrolment.getSemester()
        );
    }

    static void update(String studentID, String courseID, String semester){
        for (StudentEnrolment enrolment : enrolments){
            if (Objects.equals(enrolment.getStudentId(), studentID)){
                if(Objects.equals(enrolment.getCourseId(), courseID)){
                    if(String )
                }
            }
        }
    }

    void delete();

    void getOne();

    static void getAll() {
        System.out.println("-----------------------------------------------");
        for (StudentEnrolment enrolment : enrolments) {
            System.out.println(enrolment);
        }
        System.out.println("-----------------------------------------------");
    }
}
