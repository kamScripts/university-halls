package uni.universityhalls;

import java.util.*;

/**
 * Hall collection class, stores collections of rooms and range of feature to meet student needs
 * by find best matching hall
 */

public class Hall {
    private String name;
    private final List<FEATURE> features;
    private final Map<Integer,Room> rooms;

    public Hall (String nameIn) {
        name = nameIn;
        rooms = new HashMap<>();
        features = new ArrayList<>();
    }

    /**
     * Copy constructor
     * @param other: Instance of Hall.
     */
    public Hall (Hall other) {
        name = other.name;
        features = new ArrayList<>(other.features);
        rooms = new HashMap<>();
        other.rooms.forEach((key,val)-> rooms.put(key, new Room(val)));
    }
    public Map<Integer,Room> findAvailableRooms(ROOM_TYPE type){
        Map<Integer,Room> freeRooms = new HashMap<>();
        rooms.forEach((num, room)-> {
            if (!room.isFull() && room.getRoomType() == type) freeRooms.put(num,new Room(room));
        });
        return freeRooms;
    }

    public void addRoom(Room r) {
        rooms.put(r.getRoomNumber(),r);
    }

    public Set<Integer> getRoomsNumbers() {
        return rooms.keySet();
    }

    public Room getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    public boolean addFeature(FEATURE feature) {
        return features.add(feature);
    }

    public boolean removeFeature(FEATURE feature) {
        return features.remove(feature);
    }

    public ArrayList<FEATURE> getFeatures() {
        return new ArrayList<>(features);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
