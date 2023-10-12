
import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    String id;
    String name;
    List<Course> courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }
}

class CourseDatabase {
    List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Course getCourse(String code) {
        for (Course course : courses) {
            if (course.code.equals(code)) {
                return course;
            }
        }
        return null;
    }
}

class StudentDatabase {
    List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public Student getStudent(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }
}

class CourseRegistrationSystem {
    CourseDatabase courseDatabase;
    StudentDatabase studentDatabase;

    public CourseRegistrationSystem() {
        this.courseDatabase = new CourseDatabase();
        this.studentDatabase = new StudentDatabase();
    }

    public void addCourse(String code, String title, String description, int capacity, String schedule) {
        courseDatabase.addCourse(new Course(code, title, description, capacity, schedule));
    }

    public void addStudent(String id, String name) {
        studentDatabase.addStudent(new Student(id, name));
    }

    public void registerCourse(String studentId, String courseCode) {
        Student student = studentDatabase.getStudent(studentId);
        Course course = courseDatabase.getCourse(courseCode);

        if (student != null && course != null && course.capacity > 0) {
            student.courses.add(course);
            course.capacity--;
        }
    }

    public void dropCourse(String studentId, String courseCode) {
        Student student = studentDatabase.getStudent(studentId);
        Course course = courseDatabase.getCourse(courseCode);

        if (student != null && course != null) {
            student.courses.remove(course);
            course.capacity++;
        }
    }

    public void displayCourseListing() {
        for (Course course : courseDatabase.courses) {
            System.out.println(course.code + " - " + course.title + " (" + course.capacity + " slots available)");
        }
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse("CS101", "Introduction to Computer Science", "Learn the basics of programming.", 30, "MWF 10:00-12:00");
        system.addCourse("CS102", "Data Structures and Algorithms", "Learn about various data structures and algorithms.", 20, "TTH 14:00-16:00");

        system.addStudent("1", "Aryan");
        system.addStudent("2", "Ayush");

        system.registerCourse("1", "CS101");
        system.registerCourse("2", "CS102");

        system.displayCourseListing();

        system.dropCourse("1", "CS101");

        system.displayCourseListing();
    }
}