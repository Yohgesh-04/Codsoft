import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public boolean enrollStudent() {
        if (isFull()) {
            return false;
        } else {
            enrolledStudents++;
            return true;
        }
    }

    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
               ", Capacity: " + capacity + ", Enrolled: " + enrolledStudents + ", Schedule: " + schedule;
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!course.isFull() && !registeredCourses.contains(course)) {
            if (course.enrollStudent()) {
                registeredCourses.add(course);
                return true;
            }
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            if (course.dropStudent()) {
                registeredCourses.remove(course);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}

class CourseDatabase {
    private Map<String, Course> courses;

    public CourseDatabase() {
        this.courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public void displayAvailableCourses() {
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}

class StudentDatabase {
    private Map<String, Student> students;

    public StudentDatabase() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        CourseDatabase courseDatabase = new CourseDatabase();
        StudentDatabase studentDatabase = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);

        // Adding some sample courses
        courseDatabase.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of CS", 30, "MWF 9-10AM"));
        courseDatabase.addCourse(new Course("MATH101", "Calculus I", "Introductory Calculus", 40, "TTh 11AM-12:30PM"));
        courseDatabase.addCourse(new Course("PHYS101", "Physics I", "Introductory Physics", 35, "MWF 2-3PM"));

        // Adding some sample students
        studentDatabase.addStudent(new Student("S001", "Alice"));
        studentDatabase.addStudent(new Student("S002", "Bob"));

        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    courseDatabase.displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    Student student = studentDatabase.getStudent(studentId);
                    if (student == null) {
                        System.out.println("Student not found!");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    Course course = courseDatabase.getCourse(courseCode);
                    if (course == null) {
                        System.out.println("Course not found!");
                        break;
                    }
                    if (student.registerCourse(course)) {
                        System.out.println("Registered successfully!");
                    } else {
                        System.out.println("Registration failed!");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    student = studentDatabase.getStudent(studentId);
                    if (student == null) {
                        System.out.println("Student not found!");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    course = courseDatabase.getCourse(courseCode);
                    if (course == null) {
                        System.out.println("Course not found!");
                        break;
                    }
                    if (student.dropCourse(course)) {
                        System.out.println("Dropped successfully!");
                    } else {
                        System.out.println("Drop failed!");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
