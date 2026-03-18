package uni.universityhalls.people;


import uni.universityhalls.ROOM_TYPE;

public class Employee extends Tenant{
    private static final long serialVersionUID = 1L;
    public Employee (String name, int age, String email, Gender gender, int id){
        super(name,age,email,gender, id);
    }
    public Employee (Employee other) {
        super(other);
    }
    @Override
    public ROOM_TYPE prefferedRoomType() {
        ROOM_TYPE result;
        switch( getGender()){
            case FEMALE: result = ROOM_TYPE.EMPLOYEE_FEMALE;
                break;
            case MALE: result = ROOM_TYPE.EMPLOYEE_MALE;
                break;
            case NONBINARY: result = ROOM_TYPE.EMPLOYEE_NONBINARY;
                break;
            default: result = ROOM_TYPE.EMPTY;
                break;
        }
        return result;
    }
    @Override
    public String toString() {
        return String.format(
                "Employee(%s, %d, %s, %s, %s",
                getName(),getAge(),getEmail(),getGender(),getId()
        );
    }


}
