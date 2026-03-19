package uni.universityhalls;

import uni.universityhalls.exceptions.TenantRecordNotFound;
import uni.universityhalls.people.Tenant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TenantRegistry implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, TenantRecord> records = new HashMap<>();

    public boolean register(Tenant tenant, String hallName, String roomNumber) {
        String tenantId = tenant.getId();
        if (records.containsKey(tenantId)) return false;
        records.put(tenantId, new TenantRecord(tenant, hallName, roomNumber));
        return true;
    }
    public void deregister(String id) {
        records.remove(id);
    }
    public Tenant findById(String id) throws TenantRecordNotFound {
        TenantRecord record = getRecord(id);
        if (record == null) throw new TenantRecordNotFound(id);
        return record.getTenant();
    }
    public TenantRecord getRecord(String id) {
        return records.get(id);
    }

}
