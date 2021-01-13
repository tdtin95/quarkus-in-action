package vn.tdtin.quarkus.oidc;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/api/tenants")
public class TenantRestApi {

    @Inject
    private JsonWebToken jwt;


    @Inject
    SecurityIdentity securityIdentity;

    @Context
    private SecurityContext security;

    @GET
    @Authenticated
    public User getUser(@HeaderParam("tenantId") @NotNull String tenantId) {
        return new User(securityIdentity);
    }

    @GET
    @Path("{id}")
    @Authenticated
    public User readUser(@PathParam("id") String tenantId) {
        if(!hasJwt()) {
            throw new ClientErrorException("must use jwt", Response.Status.UNAUTHORIZED);
        }
        return new User(securityIdentity);
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }

}