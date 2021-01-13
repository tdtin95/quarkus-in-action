package vn.tdtin.quarkus.oidc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathResolverTest {

    @Test
    void getTenant_shouldGetTenantFromPath() {
       String path =  "http://192.168.85.26:8080/api/tenants/quarkus";
       Assertions.assertEquals("quarkus", PathResolver.getTenant(path));
    }

    @Test
    void getTenant_shouldGetTenantFromPath_whenThePathIsLonger() {
        String path =  "http://192.168.85.26:8080/api/tenants/quarkus/revokeToken";
        Assertions.assertEquals("quarkus", PathResolver.getTenant(path));
    }

    @Test
    void getTenant_returnNull_when_tenantIsMissing() {
        String path =  "http://192.168.85.26:8080/api/tenants/";
        Assertions.assertEquals("",PathResolver.getTenant(path));

        path =  "http://192.168.85.26:8080/api/tenants";
        Assertions.assertNull(PathResolver.getTenant(path));
    }
}
