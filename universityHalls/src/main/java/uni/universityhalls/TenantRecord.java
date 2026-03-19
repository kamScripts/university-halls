package uni.universityhalls;

import uni.universityhalls.people.Tenant;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class TenantRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Tenant tenant;
    private String hallName;
    private String roomNumber;

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
