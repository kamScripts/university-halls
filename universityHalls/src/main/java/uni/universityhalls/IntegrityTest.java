package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;

import java.util.*;


public class IntegrityTest {
    public static void main (String [] args) {

        Store controller = new Store();
        Hall h1 =new Hall("hall01");
        Hall h2 =new Hall("hall02");
        double roomCost = 400.00;
        for (int i = 1; i<=30;i++) {
            if (i<10) {
                Room r1 = new Room(i, 4, roomCost, ROOM_TYPE.EMPTY, true);
                h1.addRoom(r1);
            }
            if (i>=10) h1.addRoom(new Room(i, 4, roomCost, ROOM_TYPE.EMPTY, false));
        }
        controller.addHall(h1);
        controller.addHall(h2);
        Set<Integer> rooms = h1.getRoomsNumbers();
        int ids = 1;
        for (int r : rooms) {
            Room room = h1.getRoom(r);
            String roomNumber = Integer.toString(r);
            while (!room.isFull()) {
                String studentName = "student" + ids;
                String studentEmail = studentName + "@edu.ucen.ac.uk";
                Student s = new Student(studentName,20, studentEmail, Gender.FEMALE,ids);
                controller.addTenant(s,"hall01",roomNumber);
                ids++;
            }
        }

        Student searchResult = controller.findStudent("1");
        Student rmStudent = controller.findStudent("40");
        Student rmStudent2 = controller.findStudent("39");
        System.out.println("Search student with id=1: "+searchResult);
        System.out.println("Room 1 isFull:"+h1.getRoom(1).isFull());
        controller.removeTenant(searchResult);
        controller.removeTenant(rmStudent);
        controller.removeTenant(rmStudent2);
        Student searchResult2 = controller.findStudent("1");
        System.out.println("Search student with id=1: (after removal tenant)"+searchResult2);
        System.out.println("Room 1 isFull: "+h1.getRoom(1).isFull());
        List<FEATURE> features = new ArrayList<>();
        Map<String, List<Room>> vacantRooms = controller.findRoom(features, ROOM_TYPE.STUDENT_FEMALE, false);
        System.out.println(vacantRooms.get("hall01"));


    }
}
