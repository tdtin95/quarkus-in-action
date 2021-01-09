package vn.tdtin.quarkus.oidc;

import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/tenant")
public class TenantRestApi {

    @Inject
    SecurityIdentity securityIdentity;

    @Context
    private SecurityContext security;

    @GET
    @RolesAllowed({"Anonymous", "user"})
    public User getUser(@HeaderParam("tenantId") @NotNull String tenantId) {
        return new User(securityIdentity);
    }

}