import java.util.*;

public class EnrolmentSystem implements StudentEnrolmentManager {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();

    public static Student searchStudent(String id) {
        for (Student student : students) {
            if (Objects.equals(student.getId(), id)) {
                return student;
            }
        }
        System.out.println("");
        return null;
    }

    public static Course searchCourse(String id) {
        for (Course course : courses) {
            if (Objects.equals(course.getId(), id)) {
                return course;
            }
        }
        System.out.println("Null");
        return null;
    }


    public static void main(String[] args) {
        //INITIALIZATION
        students.add(new Student("s1111", 2002, 4, 4, "Nguyen"));
        students.add(new Student("s2222", 1998, 1, 2, "Huy"));

        courses.add(new Course("C1", "Course 1", 1));
        courses.add(new Course("C2", "Course 2", 3));
        courses.add(new Course("C3", "Course 3", 2));
        courses.add(new Course("C4", "Course 4", 4));

        int menuInput;

        do {
            //DISPLAY
            System.out.println("Enter number from the menu");
            System.out.println("1: Add Enrolment");
            System.out.println("2: Show All Enrolments");
            System.out.println("0: Exit");
            menuInput = Integer.parseInt(scanner.nextLine());

            switch (menuInput) {
                case 1:
                    System.out.println("--------------");
                    System.out.println("ADD ENROLMENT");
                    System.out.println("--------------");

                    System.out.println("Enter student id");
                    String studentId = scanner.nextLine();
                    Student chosenStudent = searchStudent(studentId);

                    boolean validStudentId = (chosenStudent != null);
                    if (validStudentId) {

                        System.out.println("Enter course id");
                        String courseId = scanner.nextLine();
                        Course chosenCourse = searchCourse(courseId);
                        boolean validCourseId = ( chosenCourse != null);
                        if (validCourseId) {

                            System.out.println("Enter semester");
                            String semester = scanner.nextLine();
                            StudentEnrolmentManager.add(chosenStudent, chosenCourse, semester);
                        } else {
                            System.out.println("Course ID entered doesn't exist");
                        }

                    } else {
                        System.out.println("Student ID entered doesn't exist");
                    }
                    break;

                case 2:
                    StudentEnrolmentManager.getAll();
                    break;
            }

        } while (menuInput != 0);


    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }
}
