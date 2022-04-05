import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EnrolmentSystem implements StudentEnrolmentManager {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static String currentStudentId;
    static String currentSemester;
    static ArrayList<StudentEnrolment> enrolments = StudentEnrolmentManager.getAll();
    static ArrayList<Integer> matchedStudentIdAndSemester = new ArrayList<>();
    static boolean updateMenu = false;


    //    public void studentEnrolmentSearch() {
//
//        System.out.println("Enter student id");
//        String studentId = scanner.nextLine();
//        ArrayList<Integer> matchedStudents = new ArrayList<>();
//
//        for (int i = 0; i < enrolments.size(); i++) {
//            if (Objects.equals(enrolments.get(i).getStudentId(), studentId)) {
//                matchedStudents.add(i);
//            }
//        }
//        boolean validStudentId = (matchedStudents.size() != 0);
//
//        if (validStudentId) {
//
//            System.out.println("Enter course id");
//            String courseId = scanner.nextLine();
//
//            ArrayList<Integer> matchedCourses = new ArrayList<>();
//
//            for (int i = 0; i < matchedStudents.size(); i++) {
//                if (Objects.equals(enrolments.get(matchedStudents.get(i)).getCourseId(), courseId)) {
//                    matchedCourses.add(matchedStudents.get(i));
//                }
//            }
//            boolean validCourseId = (matchedCourses.size() != 0);
//
//            if (validCourseId) {
//                System.out.println("Enter semester");
//                String semester = scanner.nextLine();
//
//                ArrayList<Integer> matchedStudentIdAndSemester = new ArrayList<>();
//
//                for (int i = 0; i < matchedCourses.size(); i++) {
//                    if (Objects.equals(enrolments.get(matchedCourses.get(i)).getSemester(), semester)) {
//                        matchedStudentIdAndSemester.add(matchedCourses.get(i));
//                    }
//                }
//                boolean validSemester = (matchedStudentIdAndSemester.size() != 0);
//
//                if (validSemester){
//                    matchedStudentIdAndSemester.get(0);
//                }else{
//                    System.out.println(searchStudent(studentId).getName() + "(" + studentId + ") is not enrolled in " +
//                            searchCourse(courseId).getName() + "(" + courseId + ") for semester " + semester);
//                }
//
//            } else {
//                System.out.println(searchStudent(studentId).getName() + "(" + studentId + ") is not enrolled in " + courseId);
//            }
//
//        } else {
//            System.out.println("There is no enrolment with that Student ID");
//        }
//    }
    public static void showEnrolment(ArrayList<Integer> list) {
        for (Integer i : list) {
            System.out.println(enrolments.get(i));
        }
    }

    public static void studentEnrolmentSearch(String sId, String sem) {
        String studentId;
        String semester;
        if (sId == null){
            System.out.println("Enter student id");
            studentId = scanner.nextLine();
        }else{
            studentId = sId;
        }

        ArrayList<Integer> matchedStudents = new ArrayList<>();

        for (int i = 0; i < enrolments.size(); i++) {
            if (Objects.equals(enrolments.get(i).getStudentId(), studentId)) {
                matchedStudents.add(i);
            }
        }
        boolean validStudentId = (matchedStudents.size() != 0);

        if (validStudentId) {
            currentStudentId = studentId;

            if (sId == null){
                System.out.println("Enter semester");
                semester = scanner.nextLine();
            }else{
                semester = sem;
            }


            for (int i = 0; i < matchedStudents.size(); i++) {
                if (Objects.equals(enrolments.get(matchedStudents.get(i)).getSemester(), semester)) {
                    matchedStudentIdAndSemester.add(matchedStudents.get(i));
                }
            }
            boolean validSemester = (matchedStudentIdAndSemester != null);

            if (validSemester) {
                currentSemester = semester;
                updateMenu = true;

            } else {
                System.out.println(searchStudent(currentStudentId).getName() + "(" + currentStudentId + ") is not enrolled for semester " + currentSemester);
                updateMenu = false;
            }

        } else {
            System.out.println("There is no enrolment with that Student ID");
            updateMenu = false;
        }
    }

    public static Student searchStudent(String id) {
        for (Student student : students) {
            if (Objects.equals(student.getId(), id)) {
                return student;
            }
        }
        return null;
    }

    public static Course searchCourse(String id) {
        for (Course course : courses) {
            if (Objects.equals(course.getId(), id)) {
                return course;
            }
        }
        return null;
    }

    public static void showAllEnrolment() {
        for (StudentEnrolment enrolment : enrolments) {
            System.out.println(enrolment);
        }
    }

    public static void main(String[] args) {
        //INITIALIZATION
        try (BufferedReader br = new BufferedReader(new FileReader("default.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        students.add(new Student("s1111", 2002, 4, 4, "Nguyen"));
        students.add(new Student("s2222", 1998, 1, 2, "Huy"));

        courses.add(new Course("C1", "Course 1", 1));
        courses.add(new Course("C2", "Course 2", 3));
        courses.add(new Course("C3", "Course 3", 2));
        courses.add(new Course("C4", "Course 4", 4));

        enrolments.add(new StudentEnrolment(students.get(0), courses.get(0), "2020A"));
        enrolments.add(new StudentEnrolment(students.get(0), courses.get(1), "2020A"));
        enrolments.add(new StudentEnrolment(students.get(0), courses.get(2), "2020A"));
        enrolments.add(new StudentEnrolment(students.get(0), courses.get(3), "2020C"));

        int menuInput;

        do {
            //DISPLAY
            System.out.println("---------------------------");
            System.out.println("Enter number from the menu");
            System.out.println("1: Show All Enrolments");
            System.out.println("2: Enrol A Student");
            System.out.println("3: Update Student Enrolment");

            System.out.println("0: Exit");
            menuInput = Integer.parseInt(scanner.nextLine());

            switch (menuInput) {
                case 1:
                    System.out.println("--------------");
                    System.out.println("ALL ENROLMENTS");
                    showAllEnrolment();
                    break;

                case 2:
                    System.out.println("--------------");
                    System.out.println("ADD ENROLMENT");
                    System.out.println("--------------");

                    System.out.println("Enter student id");
                    String studentId2 = scanner.nextLine();
                    Student chosenStudent2 = searchStudent(studentId2);

                    boolean validStudentId2 = (chosenStudent2 != null);
                    if (validStudentId2) {

                        System.out.println("Enter course id");
                        String courseId2 = scanner.nextLine();
                        Course chosenCourse2 = searchCourse(courseId2);
                        boolean validCourseId2 = (chosenCourse2 != null);
                        if (validCourseId2) {

                            System.out.println("Enter semester");
                            String semester2 = scanner.nextLine();
                            StudentEnrolmentManager.add(chosenStudent2, chosenCourse2, semester2);
                        } else {
                            System.out.println("Course ID entered doesn't exist");
                        }

                    } else {
                        System.out.println("Student ID entered doesn't exist");
                    }
                    break;

                case 3:
                    System.out.println("--------------");
                    System.out.println("UPDATE STUDENT ENROLMENT");
                    System.out.println("--------------");
                    studentEnrolmentSearch(null,null);
                    int menuUpdate = 0;
                    if(updateMenu){
                        do {
                            studentEnrolmentSearch(currentStudentId, currentSemester);
                            System.out.println("-----------------------------------------------");
                            System.out.println("All Courses " + searchStudent(currentStudentId).getName() + "(" + currentStudentId + ") enrolled in semester " + currentSemester);
                            System.out.println("             --------------------              ");
                            showEnrolment(matchedStudentIdAndSemester);
                            System.out.println("-----------------------------------------------");
                            System.out.println("Enter number from the menu");
                            System.out.println("1: Enrol A New Course");
                            System.out.println("2: Delete a course");
                            System.out.println("0: Exit");
                            menuUpdate = Integer.parseInt(scanner.nextLine());
                            switch (menuUpdate) {
                                case 1:
                                    Student chosenStudent3 = searchStudent(currentStudentId);

                                    boolean validStudentId3 = (chosenStudent3 != null);
                                    if (validStudentId3) {

                                        System.out.println("Enter course id");
                                        String courseId3 = scanner.nextLine();
                                        Course chosenCourse3 = searchCourse(courseId3);
                                        boolean validCourseId3 = (chosenCourse3 != null);
                                        if (validCourseId3) {
                                            StudentEnrolmentManager.add(chosenStudent3, chosenCourse3, currentSemester);
                                        } else {
                                            System.out.println("Course ID entered doesn't exist");
                                        }

                                    } else {
                                        System.out.println("Student ID entered doesn't exist");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter course id");
                                    String courseId = scanner.nextLine();

                                    ArrayList<Integer> matchedAll = new ArrayList<>();

                                    for (int i = 0; i < matchedStudentIdAndSemester.size(); i++) {
                                        if (Objects.equals(enrolments.get(matchedStudentIdAndSemester.get(i)).getCourseId(), courseId)) {
                                            matchedAll.add(matchedStudentIdAndSemester.get(i));
                                        }
                                    }
                                    boolean validCourse = (matchedAll.size() != 0);

                                    if (validCourse) {
                                        if (matchedAll.size() != 0) {
                                            StudentEnrolmentManager.delete(matchedAll.get(0));
                                        }

                                    } else {
                                        System.out.println(searchStudent(currentStudentId).getName() + "(" + currentStudentId + ") is not enrolled in " + courseId + " for semester " + currentSemester);
                                    }
                                    break;
                            }

                        } while (menuUpdate != 0);

                        if (menuUpdate == 0) {
                            updateMenu = false;
                        }
                    }




                    break;

                case 4:
                    System.out.println("--------------");
                    System.out.println("UPDATE ENROLMENT");
                    System.out.println("--------------");

            }

        } while (menuInput != 0);

        System.out.println("THank you!");


    }
}
