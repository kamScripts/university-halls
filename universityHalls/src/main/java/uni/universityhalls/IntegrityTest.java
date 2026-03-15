package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class IntegrityTest {
    public static void main (String [] args) {
        Store controller = new Store();
        Student s1 = new Student("s1", 20, "s@edu.uk", Gender.FEMALE,1);
        Employee e1 = new Employee("s1", 20, "s@edu.uk", Gender.FEMALE,1);
        Hall h1 =new Hall("hall01");
        double roomCost = 400.00;
        for (int i = 1; i<=100;i++) {
            if (i<10) {
                h1.addRoom(new Room(i, 4, roomCost, ROOM_TYPE.EMPTY, true));
            }
        }



    }
}
