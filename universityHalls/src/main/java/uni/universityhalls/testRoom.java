package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

public class testRoom {
    public static void main (String[] args) {
        Room r1 = new Room(1,3,300, ROOM_TYPE.STUDENT_MALE);
        r1.addTenant(new Student("Kam", 20, "Kam@uni.edu", Gender.MALE,200 ));
        r1.addTenant(new Student("John", 20, "Kama@uni.edu", Gender.MALE,121 ));
        r1.addTenant(new Student("Jon", 20, "Kama@uni.edu", Gender.MALE,11 ));
        r1.removeTenant(11);
        r1.addTenant(new Student("Robert", 20, "Kama@uni.edu", Gender.MALE,101 ));
        System.out.println("Room: " + r1);
        System.out.println(r1.getList());
        System.out.println(r1.findStudent("Kam"));
        System.out.println(r1.findStudent(101));
        Student s1 = new Student("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Student s2 = new Student("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Employee e1 = new Employee("Student1", 20, "student1@uni.edu", Gender.MALE,200 );
        Employee e2 = new Employee("Student1", 20, "student1@uni.edu", Gender.MALE,200 );

        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(e1));

    }
}
