package uni.universityhalls;

import uni.universityhalls.exceptions.HallNotFoundException;
import uni.universityhalls.exceptions.RoomFull;
import uni.universityhalls.exceptions.RoomNotFoundException;
import uni.universityhalls.exceptions.TenantRecordNotFound;
import uni.universityhalls.people.Tenant;
import java.io.*;
import java.util.*;

/** Orchestrator class that creates halls ,finds accommodation for new tenants * add,edit and delete data.
 *
 */
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, Hall> halls = new HashMap<>();
    private final TenantRegistry registry = new TenantRegistry();


    public boolean addTenant(Tenant tenant, String hallName, String roomNumber)
            throws HallNotFoundException, RoomNotFoundException, RoomFull {

        Hall hall = getHall(hallName);
        if (hall == null) throw new HallNotFoundException(hallName);

        Room room = hall.getRoom(roomNumber);
        if (room == null) throw new RoomNotFoundException(roomNumber);
        if (room.isFull()) throw new RoomFull(roomNumber);

        if (registry.register(tenant, hallName, roomNumber)) {
            room.addTenant();
            return true;
        }
        return false;
    }

    public void removeTenant(String id) throws TenantRecordNotFound, RoomNotFoundException {
        TenantRecord record = registry.getRecord(id);
        String hallName = record.getHallName();
        String roomNumber = record.getRoomNumber();
        registry.deregister(id);
        Hall h = getHall(record.getHallName());
        Room r = h.getRoom(roomNumber);
         if (r == null) {
             throw new RoomNotFoundException(roomNumber);
         }
         r.removeTenant();
    }


    /**Finds a selection of available based on criteria like hall's features, room type, or ground floor laction
     *
     * @param requested: List<FEATURE> - Enum also property of Hall class.
     * @param roomType: ROOM_TYPE - Enum
     * @return Map: key=Hall, value=List
     */
    public Map<String, Set<String>> findRoom(List<FEATURE> requested,ROOM_TYPE roomType, boolean groundFloor) {
        // key= hallName : value= Set of available room numbers in this hall.
        Map<String, Set<String>> results = new HashMap<>();
        for (Hall hall : halls.values()) {

            if (!hall.hasAllFeatures(requested)) {
                continue; //if not satisfies hall-lvl features skip.
            }
            String hallName = hall.getName();
            Set<String> matches = hall.findAvailableRooms(roomType, groundFloor);
            // Add record to the map only if set is not empty.
             results.put(hallName, matches);
        }
        return results;
    }
    public void addHall(Hall h) {
        halls.put(h.getName(), h);
    }
    public Hall getHall(String name) {
        return halls.get(name);
    }
    public Tenant findTenant(String id) throws TenantRecordNotFound{
        return registry.findById(id);
    }
    public TenantRecord getTenantRecord(String id) {
        return registry.getRecord(id);
    }

}
