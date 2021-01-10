package vn.tdtin.quarkus;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserRestApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public String createUser(InlineObject request) {
        SystemUser user = new SystemUser();
        user.setUsername(request.getUsername());
        user.setAddresses(request.getAddresses());
        user.persistAndFlush();
        return user.getId();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SystemUser> listUsers() {
        return SystemUser.listAll();
    }
}