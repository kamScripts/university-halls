package uni.universityhalls;
import uni.universityhalls.people.Tenant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Collection class to hold a list of tenants
 * @author Kamil Gebski
 * @version 20th Feb 2026
 * TODO: UPDATE JAVADOC
 */

public class Room {
    private final int roomNumber;
    private int capacity;
    private double cost;
    private boolean isFull;
    private boolean isGroundFloor;
    private ROOM_TYPE roomType;
    private final List<String> tenants;
    /**
     * Creates a new Room instance with the specified room number, bed count,
     * monthly cost, and room type. Newly created rooms start with no tenants
     * and are marked as not full.
     *
     * @param roomNumber the unique identifier assigned to this room
     * @param capacity       the number of capacity available in the room
     * @param cost       the nightly cost associated with booking the room
     * @param type       the {@link ROOM_TYPE} type of tenants (student/employee_gender)
     */
    public Room(int roomNumber, int capacity, double cost, ROOM_TYPE type) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.cost = cost;
        roomType = type;
        isFull = false;
        isGroundFloor = false;
        tenants = new ArrayList<>();
    }

    /** Copy Constructor
     *
     * @param other: Room object
     */
    public Room (Room other) {
        roomNumber = other.roomNumber;
        capacity = other.capacity;
        cost = other.cost;
        roomType = other.roomType;
        isFull = other.isFull;
        tenants =new ArrayList<>(other.tenants);

    }
    @Override
    public String toString() {
        return String.format(
                "Room(roomNumber=%d, capacity= %d, cost=%.2f, roomType: %s, isFull= %s, tenants= %s)",
                getRoomNumber(), getcapacity(), getCost(), getRoomType(), isFull(), tenants.toString()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Room room = (Room) other;
        return Objects.equals(getRoomNumber(), room.getRoomNumber());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber());
    }

    /**Get list of rooms
     *
     * @return ArrayList of residents' student number
     */
    public List<String> getList() {
        // return list of student numbers of room residents.
        return List.copyOf(tenants);
    }

    /** Adds new tenant to the room.
     *
     * @param id: Tenant id
     * @return Returns true if tenant added successfully.
     */
    public boolean addTenant(String id) {

        if (!isFull && !findStudent(id)) {
            tenants.add(id);
            isFull = tenants.size() >= capacity; // update isFull flag when room is full.
            return true;
        }
        return false;
    }

    /**Removes tenant from the list and update isFull flag
     *
     * @param studentNumber: student ID
     * @return Returns true if student was removed successfully
     */
    public boolean removeTenant(String studentNumber) {
        if (findStudent(studentNumber)) {
            tenants.remove(studentNumber);
            isFull = tenants.size() >= capacity;
            return true;
        }
        return false;
    }

    /** Lookup method based on tenant ID
     *
     * @param studentNumber: student ID
     * @return Returns true if tenant is on the list
     */
    public boolean findStudent(String studentNumber) {
        return tenants.contains(studentNumber);
    }

    /**
     * Returns number of occupied capacity.
     * @return current count of capacity taken.
     */
    public int get_count() {
        return tenants.size();
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
     * Returns the maximum number of capacity available in this room.
     *
     * @return number of capacity
     */
    public int getcapacity() {
        return capacity;
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

    public boolean onGroundFloor() {
        return isGroundFloor;
    }

    /**
     * Returns the type of this room (Student/Employee -gender).
     *
     * @return the room type
     */
    public ROOM_TYPE getRoomType() {
        return roomType;
    }
    public void setRoomType(ROOM_TYPE type) {
        roomType = type;
    }
    /**
     * sets a number of capacity in a room
     * @param capacity: maximum numbers of tenants
     */
    public void setcapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * sets a cost of a room
     * @param cost: new cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void clearRoom() {
        tenants.clear();
        isFull = false;
    }

}
