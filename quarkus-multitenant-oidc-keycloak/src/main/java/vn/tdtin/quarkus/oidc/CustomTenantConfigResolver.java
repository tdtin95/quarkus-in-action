package vn.tdtin.quarkus.oidc;

import io.quarkus.oidc.OidcTenantConfig;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ext.Provider;

@ApplicationScoped
public class CustomTenantConfigResolver implements io.quarkus.oidc.TenantConfigResolver {

    private static final String SPLASH = "/" ;
    @ConfigProperty(name = "security.oidc.auth-server-url")
    private String authServerUrl;

    @ConfigProperty(name = "security.oidc.client-id")
    private String clientId;
    @ConfigProperty(name = "security.token-issuer")
    private String tokenIssuer;


    @Override
    public OidcTenantConfig resolve(RoutingContext context) {
        String tenantId = PathResolver.getTenant(context.request().absoluteURI());
        if(StringUtils.isNotBlank(tenantId)) {
            OidcTenantConfig config = new OidcTenantConfig();
            config.setTenantId(tenantId);
            config.setAuthServerUrl(authServerUrl + SPLASH + tenantId);
            System.out.println(authServerUrl + SPLASH + tenantId);
            config.setClientId(clientId);
            config.setToken(OidcTenantConfig.Token.fromIssuer(tokenIssuer + SPLASH + tenantId));
            return config;
        }
        System.out.println("-----------NULL");
        return null;

    }
}
