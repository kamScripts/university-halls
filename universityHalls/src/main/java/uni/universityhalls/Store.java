package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Controller class that creates halls ,finds accommodation for new tenants
 * add,edit and delete data.
 *
 */
public class Store {
    private final Map<String, Hall> halls = new HashMap<>();
    private final Map<String, Student> studentsRegister = new HashMap<>();
    private final Map<String, Employee> uniEmployeesRegister = new HashMap<>();
    private final Map<String, String []> studentToRoomMapping = new HashMap<>(); //value [hall, room]
    private final Map<String, String []> empToRoomMapping = new HashMap<>(); //value [hall, room]

    public void addHall(Hall h) {
        halls.put(h.getName(), h);
    }

    public void addTenant(Student student,String hall, String room) {
        String id = student.getId();
        String [] location = {hall, room};

        if (!studentsRegister.containsKey(id)) {
            studentsRegister.put(id,student);
            studentToRoomMapping.put(id, location);
        }
    }
    public void addTenant(Employee emp, String hall, String room) {
        String id = emp.getId();
        String [] location = {hall, room};

        if (!studentsRegister.containsKey(id)) {
            uniEmployeesRegister.put(id,emp);
            empToRoomMapping.put(id, location);
        }
    }

    public Map<String, List<Room>> findRoom(List<FEATURE> requested,ROOM_TYPE roomType, boolean groundFloor) {

        Map<String, List<Room>> results = new HashMap<>();

        for (Hall hall : halls.values()) {

            // 1. Check hall-level features first
            if (!hall.getFeatures().containsAll(requested)) {
                continue; // hall cannot satisfy tenant requirements
            }
            String hallName = hall.getName();
            List <Room> matches = new ArrayList<>();

            for (int r : hall.getRoomsNumbers()) {

                // 2. First floor requirement
                Room roomCandidate = hall.getRoom(r);
                if (groundFloor && !roomCandidate.onGroundFloor()) continue;
                if (!roomCandidate.isFull() && (roomCandidate.getRoomType() == roomType)) matches.add(roomCandidate);
            }
            if (!matches.isEmpty()) results.put(hallName, matches);
        }
        return results;
    }




}
