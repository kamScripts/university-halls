package uni.universityhalls;

import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

public class testRoom {
    public static void main (String[] args) {
        Room r1 = new Room(1,3,300, ROOM_TYPE.STUDENT_MALE);
        r1.addTenant(new Student("kam", 20, "Kam@wp.pl", Gender.MALE,200 ));
        r1.addTenant(new Student("kamila", 20, "Kama@wp.pl", Gender.MALE,101 ));
        System.out.println(r1.getList());
        System.out.println(r1.findStudent("kam"));
    }
}
