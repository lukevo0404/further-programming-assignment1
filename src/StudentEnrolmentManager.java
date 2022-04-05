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

    static void update(Student student, Course course, String semester) {
        for (StudentEnrolment enrolment : enrolments) {
            if (Objects.equals(enrolment.getStudentId(), student.getId())) {
                if (Objects.equals(enrolment.getCourseId(), course.getId())) {
                    if (Objects.equals(enrolment.getSemester(), semester)) {
                        enrolment.setStudent(student);
                        enrolment.setCourse(course);
                        enrolment.setSemester(semester);
                    }
                }
            }
        }
    }

    static void delete(int enrolmentPosition) {
        enrolments.remove(enrolmentPosition);
        System.out.println("Successfully remove enrolment");
    }

    ;

    static void getOne(int enrolmentPosition) {
        System.out.println("-----------------------------------------------");
        System.out.println(enrolments.get(enrolmentPosition));
        System.out.println("-----------------------------------------------");
    }

    ;

    static void getAll() {
        System.out.println("-----------------------------------------------");
        for (StudentEnrolment enrolment : enrolments) {
            System.out.println(enrolment);
        }
        System.out.println("-----------------------------------------------");
    }
}
