package uni.universityhalls.people;

public class Employee extends Person implements Tenant, Comparable<Employee>{
    private final int EmployeeNumber;


    public Employee (String name, int age, String email, Gender gender, int number){
        super(name,age,email,gender);
        EmployeeNumber = number;
    }
    @Override
    public String toString() {

        return '{' +
                "EmployeeNumber= " + EmployeeNumber +
                ", name= " + getName() +
                ", age= " + getAge() +
                ", email= " + getEmail() +
                ", gender= " + getGender() +
                ", createdAt= " + getCreatedAt() +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        // Return 0 if Employee number is equal, 1 when is higher and -1 if lower.
        int thisNumber = this.EmployeeNumber;
        int anotherNumber = o.EmployeeNumber;
        return thisNumber != anotherNumber ? (thisNumber > anotherNumber ? 1 : -1) : 0;
    }

    public int getId() {
        return EmployeeNumber;
    }

}
