package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

public class testRoom {
    public static void main (String[] args) {
        Room r1 = new Room(1,3,300, ROOM_TYPE.STUDENT_MALE, true);
        Student st1 =(new Student("Kam", 20, "Kam@uni.edu", Gender.MALE,200 ));
        Student st2 =(new Student("John", 20, "Kama@uni.edu", Gender.MALE,121 ));
        Student st3 =(new Student("Jon", 20, "Kama@uni.edu", Gender.MALE,11 ));
        Student st4 =(new Student("Robert", 20, "Kama@uni.edu", Gender.MALE,101 ));
        System.out.println("Room: " + r1);
        Student s1 = new Student("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Student s2 = new Student("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Employee e1 = new Employee("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Employee e2 = new Employee("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        r1.addTenant();
        r1.addTenant();
        r1.addTenant();
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(e1));
        Room r2 = new Room(r1);
        Room r3 = r1;
        r2.removeTenant();
        r3.removeTenant();
        System.out.println(r1);

    }
}
