package uni.universityhalls;

import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

import java.util.ArrayList;


public class IntegrityTest {
    public static void main (String [] args) {
        Hall h1 = new Hall("Hall_01");
        for(int i = 1; i<6;i++){
            for(int j = 0; j<16;j++){
                h1.addRoom(new Room(i*100+j, 4, 400, ROOM_TYPE.STUDENT_MALE));
            }
        }
        ArrayList<Room> rooms = h1.getRooms();
        for(int i = 0; i < rooms.size();i++){
            Room r = rooms.get(i);
            int j=1;
            while (!r.isFull()){
                String n = "s_"+i+"_"+j;
                String e = n+"@gmail.com";
                int id = i + j;
                r.addTenant(new Student(n,19, e, Gender.FEMALE, id));
                j++;

            }
        }

    }
}
