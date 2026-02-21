package uni.universityhalls;

import java.util.ArrayList;


public class Hall {
    private String name;
    private final ArrayList<Room> rooms;

    public Hall (String nameIn) {
        name = nameIn;
        rooms = new ArrayList<>();
    }
    public void addRoom(Room r) {
        rooms.add(r);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
