package uni.universityhalls;

import java.io.Serializable;
import java.util.*;

/**
 * Hall collection class, stores collections of rooms and range of feature to meet student needs
 * by find best matching hall
 */

public class Hall implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final Set<FEATURE> features;
    private final Map<String,Room> rooms;

    public Hall (String nameIn) {
        name = nameIn;
        rooms = new HashMap<>();
        features = new HashSet<>();
    }

    /**
     * Copy constructor
     * @param other: Instance of Hall.
     */
    public Hall (Hall other) {
        name = other.name;
        features = new HashSet<>(other.features);
        rooms = new HashMap<>();
        other.rooms.forEach((key,val)-> rooms.put(key, new Room(val)));
    }
    @Override
    public String toString() {
        return name + "("+features+", "+rooms+")";
    }
    public Set<String> findAvailableRooms(ROOM_TYPE type, boolean groundFloor){
        Set<String> freeRooms = new HashSet<>();
        for (Map.Entry<String, Room> entry: rooms.entrySet()) {
            Room room = entry.getValue();
            String roomNumber = entry.getKey();
            if (groundFloor && !room.onGroundFloor())
                continue;
            if (!room.isFull() &&
                    (room.getRoomType() == ROOM_TYPE.EMPTY || room.getRoomType() == type)
            ) freeRooms.add(roomNumber);
        };
        return freeRooms;
    }

    public boolean hasAllFeatures(List<FEATURE> requested) {
        return features.containsAll(requested);
    }

    public void addRoom(Room r) {
        rooms.put(r.getRoomNumber(),r);
    }

    public Set<String> getRoomsNumbers() {
        return new HashSet<>(rooms.keySet());
    }

    public Room getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public boolean addFeature(FEATURE feature) {
        return features.add(feature);
    }

    public boolean removeFeature(FEATURE feature) {
        return features.remove(feature);
    }

    public Set<FEATURE> getFeatures() {
        return new HashSet<>(features);
    }

    public String getName() {
        return name;
    }
    public int countEmptyRooms(){
        int total = 0;
        for (Room room: rooms.values()) {
            if (room.isEmpty()) total++;
        }
        return total;
    }
    public int getTotalBeds () {
        int total = 0;
        for (Room room: rooms.values()) {
            total+=room.getCapacity();
        }
        return total;
    }
    public int getEmptyBeds () {
        int total = 0;
        for (Room room: rooms.values()) {
            total+=(room.getCapacity()-room.getCount());
        }
        return total;
    }

}
