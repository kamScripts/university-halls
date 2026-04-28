package uni.universityhalls;


import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;


import java.util.*;


public class IntegrityTest {
    public static void main (String [] args) {
        String hallName = "hall01";
        List<FEATURE> requirements = new ArrayList<>();


        //Create Store to hold halls tenants
        Store store = new Store();
        // Load from a drive
        //Store store = StoreRepository.load("store1.dat");
        // Each store - different features and set of rooms
        Hall h2 =new Hall(hallName);
        h2.addRoom(new Room("1",2, ROOM_TYPE.EMPTY, true));
        h2.addRoom(new Room("2",2, ROOM_TYPE.EMPTY, false));
        store.addHall(h2);
        System.out.println(store.getHall("hall01"));
        /*
        //System.out.println(store.getHall("hall01"));// Add hall with rooms.
        Student s1 = new Student("student1", 20, "s@edu.co.uk", Gender.FEMALE,"1");
        Student s2 = new Student("student1", 20, "s@edu.co.uk", Gender.FEMALE,"2");
        Student s3 = new Student("student1", 20, "s@edu.co.uk", Gender.FEMALE,"3");
        Student s4 = new Student("student1", 20, "s@edu.co.uk", Gender.FEMALE,"4");

        store.addTenant(s1, hallName, "1");
        store.addTenant(s2, hallName, "1");
        store.addTenant(s3, hallName, "2");
        store.addTenant(s4, hallName, "2");
        Map<String, Set<String>> m = store.findRoom(requirements,s1.preferredRoomType(),true);
        System.out.println("findRoom room full: "+m.get(hallName));
        store.removeTenant("1");
        m = store.findRoom(requirements,s1.preferredRoomType(),true);
        System.out.println("findRoom room not-full: "+m.get(hallName));
        //StoreRepository.save(store, "store1.dat");


        //requirements.add(FEATURE.ALL_DAY_ASSISTANCE);
        Map<String, Set<String>>freeRooms=store.findRoom(requirements, ROOM_TYPE.EMPLOYEE_FEMALE, false);
        System.out.println(freeRooms.get(hallName));
        Room r = store.getHall(hallName).getRoom("4");
        System.out.println(r);

         */

    }
}
