package uni.universityhalls;

import uni.universityhalls.people.Tenant;
import java.io.Serializable;
import java.util.Objects;

/** Collection class to hold a list of tenants
 * @author KG
 * @version 20th Feb 2026
 * TODO: UPDATE JAVADOC
 */

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String roomNumber;
    private int capacity;
    private final boolean isGroundFloor;
    private ROOM_TYPE roomType;
    private int tenants;
    /**
     * Creates a new Room instance with the specified room number, bed count,
     * and room type. Newly created rooms start with no tenants
     * and are marked as not full.
     *
     * @param roomNumber the unique identifier assigned to this room
     * @param capacity       the number of capacity available in the room
     * @param roomType       the {@link ROOM_TYPE} type of tenants (student/employee_gender)
     * @param isGroundFloor ground-floor flag
     */
    public Room(String roomNumber, int capacity,  ROOM_TYPE roomType, boolean isGroundFloor) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomType = roomType;
        this.isGroundFloor = isGroundFloor;
        this.tenants = 0;
    }

    /** Copy Constructor
     *
     * @param other: Room object
     */
    public Room(Room other) {
        this.roomNumber = other.roomNumber;
        this.capacity = other.capacity;
        this.roomType = other.roomType;
        this.isGroundFloor = other.isGroundFloor;
        this.tenants = other.tenants;
    }

    @Override
    public String toString() {
        return String.format(
                "Room(roomNumber=%s, capacity= %d, roomType: %s, isFull= %s, tenants= %d, isGroundFloor= %s)",
                getRoomNumber(), getCapacity(), getRoomType(), isFull(), tenants, isGroundFloor
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Room room = (Room) other;
        return roomNumber.equals(room.getRoomNumber());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber());
    }

    /** Adds new tenant to the room.
     *
     */
    public void addTenant() {
        if (!isFull()) tenants+=1;
    }
    /**Removes tenant from the list and update isFull flag
     *
     *
     */
    public void removeTenant() {
        if (tenants>0)  tenants-= 1;
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

    public String getRoomNumber() {
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
     * Indicates whether the room has reached its maximum occupancy.
     *
     * @return true if the room is full, false otherwise
     */
    public boolean isFull() {
        return tenants>=capacity;
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

    public void decideRoomType(Tenant tenant) {
        setRoomType(tenant.preferredRoomType());
    }

    /**
     * sets a number of capacity in a room
     * @param capacity: maximum numbers of tenants
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void clearRoom() {
        tenants = 0;
        roomType = ROOM_TYPE.EMPTY;
    }

}
