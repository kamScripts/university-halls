package uni.universityhalls;

import java.util.ArrayList;
import java.util.List;

/**
 * Hall collection class, stores collections of rooms and range of feature to meet student needs
 * by find best matching hall
 */

public class Hall {
    private String name;
    private final List<FEATURE> features;
    private final List<Room> rooms;

    public Hall (String nameIn) {
        name = nameIn;
        rooms = new ArrayList<>();
        features = new ArrayList<>();
    }
    public List<Room> findAvailableRooms(){
        List<Room> freeRooms = new ArrayList<>();
        for (Room r : getRooms())
        {
            if (!r.isFull()) freeRooms.add(r);
        }
        return freeRooms;
    }
    public void addRoom(Room r) {
        rooms.add(r);
    }

    public ArrayList<Room> getRooms() {
        return new ArrayList<>(rooms);
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
