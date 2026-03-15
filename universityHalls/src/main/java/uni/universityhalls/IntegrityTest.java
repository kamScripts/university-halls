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

        controller.addTenant(s1);
        controller.addTenant(e1);

    }
}
