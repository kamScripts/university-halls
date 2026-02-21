package uni.universityhalls;

import uni.universityhalls.people.Person;
import uni.universityhalls.people.Tenant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Collection class to hold a list of tenants
 * @author Kamil Gebski
 * @version 20th Feb 2026
 * TODO: move Tennants storage to Main controller and create map tenant-room or
 * TODO: something like that and keep only list of Ids as reference inside Room.
 */

public class Room {
    private final int roomNumber;
    private int beds;
    private double cost;
    private final Map<String, Tenant> tenants;
    private boolean isFull;
    private final ROOM_TYPE roomType;

    /**
     * Creates a new Room instance with the specified room number, bed count,
     * monthly cost, and room type. Newly created rooms start with no tenants
     * and are marked as not full.
     *
     * @param roomNumber the unique identifier assigned to this room
     * @param beds       the number of beds available in the room
     * @param cost       the nightly cost associated with booking the room
     * @param type       the {@link ROOM_TYPE} type of tenants (student/employee_gender)
     */
    public Room(int roomNumber, int beds, double cost, ROOM_TYPE type) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.cost = cost;
        tenants = new HashMap<>();
        isFull = false;
        roomType = type;

    }
    @Override
    public String toString() {
        return Integer.toString(roomNumber);
    }

    /**Get list of tenants
     *
     * @return set of residents' student number
     */
    public Set<String> getList() {
        // return list of student no. of room residents.
        return tenants.keySet();
    }

    /** Adds new tenant to the room.
     *
     * @param person: new Tenant (Student/Employee)
     * @return Returns true if tenant added successfully.
     */
    public boolean addTenant(Tenant person) {
        String id = person.getId();
        if (!isFull && !findStudent(id)) {
            tenants.put(id, person);
            isFull = tenants.size() >= beds; // update isFull flag when room is full.
            return true;
        }
        return false;
    }

    /**Removes tenant from the list and update isFull flag
     *
     * @param studentNumber: student ID
     * @return Returns true if student was removed successfully
     */
    public boolean removeTenant(int studentNumber) {
        if (findStudent(studentNumber)) {
            tenants.remove(studentNumber);
            isFull = tenants.size() >= beds;
            return true;
        }
        return false;
    }

    /** Lookup method based on tenant ID
     *
     * @param studentNumber: student ID
     * @return Returns true if tenant is on the list
     */
    public boolean findStudent(int studentNumber) {
        return tenants.containsKey(studentNumber);
    }

    /** Lookup method based on tenant name
     *
     * @param name: tenant name
     * @return Returns true if tenant is on the list
     */
    public boolean findStudent(String name) {
        for(String key: tenants.keySet()) {
            if (    // cast Person type to access getName method.
                    ((Person) tenants.get(key)).getName().equals(name)
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the room number assigned to this room.
     *
     * @return the room number
     */

    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the maximum number of beds available in this room.
     *
     * @return number of beds
     */
    public int getBeds() {
        return beds;
    }

    /**
     * Returns the cost associated with renting this room.
     *
     * @return room cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Indicates whether the room has reached its maximum occupancy.
     *
     * @return true if the room is full, false otherwise
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * Returns the type of this room (Student/Employee -gender).
     *
     * @return the room type
     */
    public ROOM_TYPE getRoomType() {
        return roomType;
    }

    /**
     * sets a number of beds in a room
     * @param beds: maximum numbers of tenants
     */
    public void setBeds(int beds) {
        this.beds = beds;
    }

    /**
     * sets a cost of a room
     * @param cost: new cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
