package uni.universityhalls.exceptions;

public class TenantRecordNotFound extends RuntimeException {
    public TenantRecordNotFound(String tenantId) {
        super("Tenant with id " + tenantId + " not found");
    }
}
