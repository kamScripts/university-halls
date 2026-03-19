package uni.universityhalls.people;

import uni.universityhalls.ROOM_TYPE;

public class Student extends Tenant {
    private static final long serialVersionUID = 1L;

    public Student (String name, int age, String email, Gender gender, String id){
        super(name,age,email,gender, id);

    }
    public Student (Student other) {
        super(other);
    }
    @Override
    public ROOM_TYPE preferredRoomType() {
        ROOM_TYPE result;
        switch( getGender()){
            case FEMALE: result = ROOM_TYPE.STUDENT_FEMALE;
                break;
            case MALE: result = ROOM_TYPE.STUDENT_MALE;
                break;
            case NONBINARY: result = ROOM_TYPE.STUDENT_NONBINARY;
                break;
            default: result = ROOM_TYPE.EMPTY;
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "Student(%s, %d, %s, %s, %s)",
                getName(),getAge(),getEmail(),getGender(),getId()
        );
    }


}
