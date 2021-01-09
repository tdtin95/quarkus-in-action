package vn.tdtin.quarkus.oidc;

import io.quarkus.oidc.OidcTenantConfig;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;

@ApplicationScoped
public class CustomTenantConfigResolver implements io.quarkus.oidc.TenantConfigResolver {

    @ConfigProperty(name = "security.oidc.auth-server-url")
    private String authServerUrl;

    @ConfigProperty(name = "security.oidc.client-id")
    private String clientId;


    @Override
    public OidcTenantConfig resolve(RoutingContext context) {
        String tenantId = context.request().getHeader("tenantId");
        OidcTenantConfig config = new OidcTenantConfig();

        config.setTenantId(tenantId);
        config.setAuthServerUrl(authServerUrl + tenantId);
        config.setClientId(clientId);
        return config;
    }
}
