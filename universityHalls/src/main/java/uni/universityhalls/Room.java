package uni.universityhalls;
import uni.universityhalls.people.Employee;
import uni.universityhalls.people.Gender;
import uni.universityhalls.people.Student;
import uni.universityhalls.people.Tenant;

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
    private int tenants;
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
    public Room(int roomNumber, int capacity, double cost, ROOM_TYPE type, Boolean groundFloor) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.cost = cost;
        roomType = type;
        isFull = false;
        isGroundFloor = groundFloor;
        tenants = 0;
    }

    /** Copy Constructor
     *
     * @param other: Room object
     */
    public Room(Room other) {
        this.roomNumber = other.roomNumber;
        this.capacity = other.capacity;
        this.cost = other.cost;
        this.roomType = other.roomType;
        this.isFull = other.isFull;
        this.isGroundFloor = other.isGroundFloor;
        this.tenants = other.tenants;
    }

    @Override
    public String toString() {
        return String.format(
                "Room(roomNumber=%d, capacity= %d, cost=%.2f, roomType: %s, isFull= %s, tenants= %d)",
                getRoomNumber(), getCapacity(), getCost(), getRoomType(), isFull(), tenants
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Room room = (Room) other;
        return this.roomNumber == room.roomNumber;
    }
    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber());
    }



    /** Adds new tenant to the room.
     *
     */
    public void addTenant() {
        if (tenants < capacity) {
            tenants++;
            isFull = tenants == capacity;
        }
    }



    /**Removes tenant from the list and update isFull flag
     *
     *
     */
    public void removeTenant() {
        if (tenants > 0) {
            tenants--;
            isFull = tenants == capacity;
        }
    }


    /**
     * Returns number of occupied capacity.
     * @return current count of capacity taken.
     */
    public int getCount() {
        return tenants;
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
    public int getCapacity() {
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
    // Change Room type based on tenant type.
    private ROOM_TYPE typeFor(Tenant tenant) {
        Gender g = tenant.getGender();

        if (tenant instanceof Student) {
            switch (g) {
                case FEMALE:
                    return ROOM_TYPE.STUDENT_FEMALE;
                case MALE:
                    return ROOM_TYPE.STUDENT_MALE;
                case NON_BINARY:
                    return ROOM_TYPE.STUDENT_NONBINARY;
                default:
                    return ROOM_TYPE.EMPTY;
            }
        }

        if (tenant instanceof Employee) {
            switch (g) {
                case FEMALE:
                    return ROOM_TYPE.EMPLOYEE_FEMALE;
                case MALE:
                    return ROOM_TYPE.EMPLOYEE_MALE;
                case NON_BINARY:
                    return ROOM_TYPE.EMPLOYEE_NONBINARY;
                default:
                    return ROOM_TYPE.EMPTY;
            }
        }

        return ROOM_TYPE.EMPTY;
    }
    public void decideRoomType(Tenant tenant) {
        setRoomType(typeFor(tenant));
    }

    /**
     * sets a number of capacity in a room
     * @param capacity: maximum numbers of tenants
     */
    public void setCapacity(int capacity) {
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
        tenants = 0;
        isFull = false;
    }

}
