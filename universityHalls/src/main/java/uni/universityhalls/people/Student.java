package uni.universityhalls.people;

import java.util.ArrayList;

public class Student extends Tenant {

    public Student (String name, int age, String email, Gender gender, int id){
        super(name,age,email,gender, id);

    }
    public Student (Student other) {
        super(other);
    }

    @Override
    public String toString() {
        return String.format(
                "Student(%s, %d, %s, %s, %s)",
                getName(),getAge(),getEmail(),getGender(),getId()
        );
    }


}
