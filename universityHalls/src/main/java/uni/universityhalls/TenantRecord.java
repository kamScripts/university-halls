package uni.universityhalls;

import uni.universityhalls.people.Tenant;

import java.io.Serializable;

/**Tenant Record class, holding Tenant with associated hall and room
 * TODO: add copy constructor
 */

public class TenantRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Tenant tenant;
    private final String hallName;
    private final String roomNumber;

    TenantRecord(Tenant tenant, String hallName, String roomNumber) {
        this.tenant = tenant;
        this.hallName = hallName;
        this.roomNumber = roomNumber;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public String getHallName() {
        return hallName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
