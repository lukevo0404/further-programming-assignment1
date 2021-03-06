import java.io.*;
import java.util.*;

public class EnrolmentSystem implements StudentEnrolmentManager {

    static Scanner scanner = new Scanner(System.in);
    static Set<Student> students = new HashSet<> ();
    static Set<Course> courses = new HashSet<> ();
    static String currentStudentId;
    static String currentSemester;
    static ArrayList<StudentEnrolment> enrolments = StudentEnrolmentManager.getAll();
    static ArrayList<Integer> matchedStudentIdAndSemester = new ArrayList<>();
    static boolean updateMenu = false;
    static ArrayList<StudentEnrolment> displayEnrolments = new ArrayList<>();



    public static void showEnrolment(ArrayList<Integer> list) {
        for (Integer i : list) {
            System.out.println(enrolments.get(i));
            displayEnrolments.add((enrolments).get(i));
        }
    }

    public static void studentEnrolmentSearch(String sId, String sem) {
        String studentId;
        String semester;
        if (sId == null) {
            System.out.println("Enter student id:");
            studentId = scanner.nextLine();
        } else {
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

            if (sId == null) {
                System.out.println("Enter semester:");
                semester = scanner.nextLine();
            } else {
                semester = sem;
            }


            for (Integer matchedStudent : matchedStudents) {
                if (Objects.equals(enrolments.get(matchedStudent).getSemester(), semester)) {
                    matchedStudentIdAndSemester.add(matchedStudent);
                }
            }
            boolean validSemester = (matchedStudentIdAndSemester != null);

            if (validSemester) {
                currentSemester = semester;
                updateMenu = true;

            } else {
                System.out.println(Objects.requireNonNull(searchStudent(currentStudentId)).getName() + "(" + currentStudentId + ") is not enrolled for semester " + currentSemester);
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

    public static void main(String[] args) throws IOException {

        //INITIALIZATION
        String fileName = "default.csv";
        System.out.println("Do you have your enrolment .csv file in the root folder? (Y/N)");
        String input = scanner.nextLine();
        boolean fileNameInput = false;

        if (Objects.equals(input, "Y") || Objects.equals(input, "y")){
            do{
                System.out.println("Type the file name:");
                fileName = scanner.nextLine();
                if(new File(fileName).isFile()){
                    System.out.println("Enrolments from " + fileName + " will be imported...");
                    fileNameInput = true;
                }else{
                    System.out.println("The file " + fileName + " doesn't exist in root folder");
                    System.out.println("Do you want to retype the file name? If not, the default enrolments will be imported. (Y/N)");
                    String input2 = scanner.nextLine();
                    if (Objects.equals(input2, "N") || Objects.equals(input2, "n")){
                        fileNameInput = true;
                        fileName = "default.csv";
                    }
                }
            }while(!fileNameInput);

        } else if (Objects.equals(input, "n") || Objects.equals(input, "N")){
            fileName = "default.csv";
            System.out.println("The default enrolments will be imported...");
        }

        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        ArrayList<String> rows = new ArrayList<>();

        while (sc.hasNext()) {
            rows.add(sc.nextLine());
        }
        for (String s : rows) {
            int col = 0;
            String[] cell = s.split(",");
            while (col < 7) {
                String studentId = cell[col];
                col++;
                String name = cell[col];
                col++;
                String birthdate = cell[col];
                col++;
                String[] birthdateParts = birthdate.split("/");
                int month = Integer.parseInt(birthdateParts[0]);
                int day = Integer.parseInt(birthdateParts[1]);
                int year = Integer.parseInt(birthdateParts[2]);
                Student newStudent = new Student(studentId, year, month, day, name);
                students.add(newStudent);

                String courseId = cell[col];
                col++;
                String courseName = cell[col];
                col++;
                int credit = Integer.parseInt(cell[col]);
                col++;
                Course newCourse = new Course(courseId, courseName, credit);
                courses.add(newCourse);

                String semester = cell[col];
                col++;
                enrolments.add(new StudentEnrolment(searchStudent(studentId), searchCourse(courseId), semester));
            }
        }
        System.out.println("All enrolments are imported:");

        for (Student student : students) {
            System.out.println(student);
        }
        for (Course course : courses) {
            System.out.println(course);
        }

        showAllEnrolment();

        int menuInput;

        do {
            //DISPLAY
            System.out.println("---------------------------");
            System.out.println("Enter number from the menu");
            System.out.println("1: Show All Students Of A Course In A Semester");
            System.out.println("2: Show All Courses Offered In A Semester");
            System.out.println("3: Enrol A Student");
            System.out.println("4: Update Student Enrolment");
            System.out.println("0: Exit");
            menuInput = Integer.
                    parseInt(scanner.nextLine());

            switch (menuInput) {
                case 1:
                    System.out.println("--------------");
                    System.out.println("ALL STUDENTS IN A COURSE");

                    System.out.println("Enter course id:");
                    String course6 = scanner.nextLine();

                    ArrayList<Integer> matchedCourse = new ArrayList<>();

                    for (int i = 0; i < enrolments.size(); i++) {
                        if (Objects.equals(enrolments.get(i).getCourseId(), course6)) {
                            matchedCourse.add(i);
                        }
                    }

                    boolean validCourse = (matchedCourse.size() != 0);
                    if (validCourse) {
                        System.out.println("Enter semester:");
                        String semester = scanner.nextLine();

                        ArrayList<Integer> matchedCourseAndSemester = new ArrayList<>();

                        for (int i = 0; i < matchedCourse.size(); i++) {
                            if (Objects.equals(enrolments.get(matchedCourse.get(i)).getSemester(), semester)) {
                                matchedCourseAndSemester.add(i);
                            }
                        }

                        boolean validSemester = (matchedCourseAndSemester.size() != 0);
                        if (validSemester) {
                            Set<String> displayStudentIds = new HashSet<>();

                            for (Integer integer : matchedCourseAndSemester) {
                                displayStudentIds.add(enrolments.get(integer).getStudentId());
                            }
                            ArrayList<Student> displayStudent = new ArrayList<>();


                            for(String displayStudentId : displayStudentIds){
                                displayStudent.add(searchStudent(displayStudentId));
                            }
                            for (Student value : displayStudent) {
                                System.out.println(value);
                            }
                            System.out.println("Do you want to export these into a CSV file? (Y/N)");
                            String input2 = scanner.nextLine();
                            if (Objects.equals(input2, "Y") || Objects.equals(input2, "y")){
                                String fileName1 =
                                        Objects.requireNonNull(searchCourse(course6)).getName() + " "
                                                + Objects.requireNonNull(searchCourse(course6)).getId() + " " + semester + ".csv";
                                FileWriter export = new FileWriter(fileName1);
                                for (Student student : displayStudent){
                                    export.append(student.getName() + "," + student.getId() + "," + student.getBirthdate());
                                    export.append('\n');
                                }
                                export.flush();
                                export.close();
                                System.out.println(fileName1 + " is saved in root folder");
                            }
                        }else{
                            System.out.println("Semester " + semester + " doesn't exist");
                        }

                    } else {
                        System.out.println("Course " + course6 + " doesn't exist");
                    }
                    break;
                case 2:
                    System.out.println("--------------");
                    System.out.println("ALL COURSES OFFERED IN A SEMESTER");
                    System.out.println("--------------");

                    System.out.println("Enter a semester:");
                    String semester = scanner.nextLine();

                    ArrayList<Integer> matchedSemester = new ArrayList<>();

                    for (int i = 0; i < enrolments.size(); i++) {
                        if (Objects.equals(enrolments.get(i).getSemester(), semester)) {
                            matchedSemester.add(i);
                        }
                    }

                    boolean validSemester = (matchedSemester.size() != 0);
                    if (validSemester) {
                        Set<String> displayCourseIds = new HashSet<>();

                        for (Integer integer : matchedSemester) {
                            displayCourseIds.add(enrolments.get(integer).getCourseId());
                        }
                        ArrayList<Course> displayCourse = new ArrayList<>();


                        for(String displayCourseId : displayCourseIds){
                            displayCourse.add(searchCourse(displayCourseId));
                        }
                        for (Course course : displayCourse) {
                            System.out.println(course);
                        }
                        System.out.println("Do you want to export these into a CSV file? (Y/N)");
                        String input2 = scanner.nextLine();
                        if (Objects.equals(input2, "Y") || Objects.equals(input2, "y")){
                            FileWriter export = new FileWriter(semester + ".csv");
                            for (Course course : displayCourse){
                                export.append(course.getName() + "," + course.getId() + "," + course.getCredit());
                                export.append('\n');
                            }
                            export.flush();
                            export.close();
                            System.out.println(semester + ".csv is saved in root folder");
                        }

                    } else {
                        System.out.println("Semester " + semester + " doesn't exist");
                    }
                    break;
                case 3:
                    System.out.println("--------------");
                    System.out.println("ADD ENROLMENT");
                    System.out.println("--------------");

                    System.out.println("Enter student id:");
                    String studentId2 = scanner.nextLine();
                    Student chosenStudent2 = searchStudent(studentId2);

                    boolean validStudentId2 = (chosenStudent2 != null);
                    if (validStudentId2) {

                        System.out.println("Enter course id:");
                        String courseId2 = scanner.nextLine();
                        Course chosenCourse2 = searchCourse(courseId2);
                        boolean validCourseId2 = (chosenCourse2 != null);
                        if (validCourseId2) {

                            System.out.println("Enter semester:");
                            String semester2 = scanner.nextLine();
                            StudentEnrolmentManager.add(chosenStudent2, chosenCourse2, semester2);
                        } else {
                            System.out.println("Course ID entered doesn't exist");
                        }

                    } else {
                        System.out.println("Student ID entered doesn't exist");
                    }
                    break;

                case 4:
                    System.out.println("--------------");
                    System.out.println("UPDATE STUDENT ENROLMENT");
                    System.out.println("--------------");
                    studentEnrolmentSearch(null, null);
                    int menuUpdate;
                    if (updateMenu) {
                        do {
                            studentEnrolmentSearch(currentStudentId, currentSemester);
                            System.out.println("-----------------------------------------------");
                            System.out.println("All Courses " + Objects.requireNonNull(searchStudent(currentStudentId)).getName() + "(" + currentStudentId + ") enrolled in semester " + currentSemester);
                            System.out.println("             --------------------              ");
                            showEnrolment(matchedStudentIdAndSemester);
                            System.out.println("-----------------------------------------------");
                            System.out.println("Enter number from the menu");
                            System.out.println("1: Enrol A New Course");
                            System.out.println("2: Delete a course");
                            System.out.println("3: Export records into a CSV file");
                            System.out.println("0: Exit");
                            menuUpdate = Integer.parseInt(scanner.nextLine());
                            switch (menuUpdate) {
                                case 1:
                                    Student chosenStudent3 = searchStudent(currentStudentId);

                                    boolean validStudentId3 = (chosenStudent3 != null);
                                    if (validStudentId3) {

                                        System.out.println("Enter course id:");
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
                                    System.out.println("Enter course id:");
                                    String courseId = scanner.nextLine();

                                    ArrayList<Integer> matchedAll = new ArrayList<>();

                                    for (Integer integer : matchedStudentIdAndSemester) {
                                        if (Objects.equals(enrolments.get(integer).getCourseId(), courseId)) {
                                            matchedAll.add(integer);
                                        }
                                    }
                                    boolean validCourse2 = (matchedAll.size() != 0);

                                    if (validCourse2) {
                                        if (matchedAll != null) {
                                            StudentEnrolmentManager.delete(matchedAll.get(0));
                                        }

                                    } else {
                                        System.out.println(Objects.requireNonNull(searchStudent(currentStudentId)).getName() + "(" + currentStudentId + ") is not enrolled in " + courseId + " for semester " + currentSemester);
                                    }
                                    break;
                                case 3:
                                    String fileName3 =  Objects.requireNonNull(searchStudent(currentStudentId)).getName() + "(" + currentStudentId + ") " + currentSemester + ".csv";
                                    FileWriter export = new FileWriter(fileName3);
                                    for (StudentEnrolment enrolment : displayEnrolments){
                                        export.append(enrolment.toString());
                                        export.append('\n');
                                    }
                                    export.flush();
                                    export.close();
                                    System.out.println(fileName3 + " is saved in root folder");
                            }

                        } while (menuUpdate != 0);

                        updateMenu = false;
                    }


                    break;
            }

        } while (menuInput != 0);

        System.out.println("THank you!");


    }
}
