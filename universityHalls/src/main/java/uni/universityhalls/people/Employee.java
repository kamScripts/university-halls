package uni.universityhalls.people;

import java.util.ArrayList;

public class Employee extends Tenant {

    public Employee (String name, int age, String email, Gender gender, int id){
        super(name,age,email,gender, id);
    }
    public Employee (Employee other) {
        super(other);
    }
    @Override
    public String toString() {
        return String.format(
                "Employee(%s, %d, %s, %s, %s",
                getName(),getAge(),getEmail(),getGender(),getId()
        );
    }


}
