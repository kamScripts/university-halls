package uni.universityhalls;

import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;
import uni.universityhalls.people.Tenant;

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

    private boolean registerTenant(Tenant tenant, String id, String[] location) {

        if (tenant instanceof Student) {
            if (studentsRegister.containsKey(id)) return false;

            studentsRegister.put(id, (Student) tenant);
            studentToRoomMapping.put(id, location);
            return true;
        }

        if (tenant instanceof Employee) {
            if (uniEmployeesRegister.containsKey(id)) return false;

            uniEmployeesRegister.put(id, (Employee) tenant);
            empToRoomMapping.put(id, location);
            return true;
        }

        return false; // unknown tenant type
    }
    public void addTenant(Tenant tenant, String hall, String room) {

        String id = tenant.getId();
        String[] location = { hall, room };
        int roomNumber = Integer.parseInt(room);

        Hall h = halls.get(hall);
        if (h == null) {
            throw new IllegalArgumentException("Hall does not exist: " + hall);
        }

        Room r = h.getRoom(roomNumber);
        if (r == null) {
            throw new IllegalArgumentException("Room does not exist: " + room);
        }

        if (r.isFull()) {
            throw new IllegalStateException("Room is full: " + room);
        }

        boolean success = registerTenant(tenant, id, location);

        if (success) {
            r.addTenant();

            if (r.getRoomType() == ROOM_TYPE.EMPTY) {
                r.decideRoomType(tenant);
            }
        }
    }

    public void removeTenant(Tenant tenant) {

        String id = tenant.getId();
        String[] location;

        if (tenant instanceof Student) {

            location = studentToRoomMapping.get(id);
            if (location == null) {
                throw new IllegalStateException("Student mapping not found for id: " + id);
            }

            studentsRegister.remove(id);
            studentToRoomMapping.remove(id);

        } else if (tenant instanceof Employee) {

            location = empToRoomMapping.get(id);
            if (location == null) {
                throw new IllegalStateException("Employee mapping not found for id: " + id);
            }

            uniEmployeesRegister.remove(id);
            empToRoomMapping.remove(id);

        } else {
            throw new IllegalArgumentException("Unknown tenant type: " + tenant.getClass());
        }

        String hall = location[0];
        String room = location[1];

        Room r = halls.get(hall).getRoom(Integer.parseInt(room));
        r.removeTenant();
    }


    public Student findStudent(String id) {
        return studentsRegister.getOrDefault(id, null);
    }
    public Employee findTenant(String id, Boolean isEmp) {
        return uniEmployeesRegister.getOrDefault(id, null);
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
                if (!roomCandidate.isFull() &&
                        (
                                roomCandidate.getRoomType() == ROOM_TYPE.EMPTY ||
                                roomCandidate.getRoomType() == roomType
                        )) matches.add(roomCandidate);
            }
            if (!matches.isEmpty()) results.put(hallName, matches);
        }
        return results;
    }




}
