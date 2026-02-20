package uni.universityhalls.people;

import java.util.ArrayList;

public class Student extends Person implements Tenant, Comparable<Student>{

    private final int studentNumber;


    public Student (String name, int age, String email, Gender gender, int number){
        super(name,age,email,gender);
        studentNumber = number;
    }
    @Override
    public String toString() {
        return Integer.toString(studentNumber);
    }

    @Override
    public int compareTo(Student o) {
        // Return 0 if student number is equal, 1 when is higher and -1 if lower.
        int thisNumber = this.studentNumber;
        int anotherNumber = o.studentNumber;
        return thisNumber != anotherNumber ? (thisNumber > anotherNumber ? 1 : -1) : 0;
    }


    public int getId() {
        return studentNumber;
    }

}
