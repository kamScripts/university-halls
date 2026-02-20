package uni.universityhalls;

import uni.universityhalls.people.Person;
import uni.universityhalls.people.Tenant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Room {
    private final int roomNumber;
    private int beds;
    private double cost;
    private final Map<Integer, Tenant> tenants;
    private boolean isFull;
    private final ROOM_TYPE roomType;

    public Room(int roomNumber, int beds, double cost, ROOM_TYPE type) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.cost = cost;
        tenants = new HashMap<>();
        isFull = false;
        roomType = type;

    }


    public Set<Integer> getList() {
        // return list of student no. of room residents.
        return tenants.keySet();
    }

    public boolean addTenant(Tenant person) {
        int id = person.getId();
        if (!isFull && !findStudent(id)) {
            tenants.put(id, person);
            isFull = tenants.size() >= beds; // update isFull flag when room is full.
            return true;
        }
        return false;
    }
    public boolean removeTenant(int studentNumber) {
        if (findStudent(studentNumber)) {
            tenants.remove(studentNumber);
            return true;
        }
        return false;
    }

    public boolean findStudent(int studentNumber) {
        return tenants.containsKey(studentNumber);
    }
    public boolean findStudent(String name) {
        for(int key: tenants.keySet()) {
            if (    // cast Person type to access getName method.
                    ((Person) tenants.get(key)).getName().equals(name)
            ) {
                return true;
            }
        }
        return false;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public double getCost() {
        return cost;
    }

    public boolean isFull() {
        return isFull;
    }

    public ROOM_TYPE getRoomType() {
        return roomType;
    }

}
