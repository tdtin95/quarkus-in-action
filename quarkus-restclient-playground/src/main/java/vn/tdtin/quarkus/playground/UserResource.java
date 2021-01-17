package vn.tdtin.quarkus.playground;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final Set<User> users = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public UserResource() {
        users.add(new User("tdtin", "code lover"));
        users.add(new User("Steave Jobs", "Hello from heaven"));
        users.add(new User("Dracula", "Hello from hell"));
    }

    @GET
    public Set<User> list() {
        return users;
    }

    @POST
    public Response addNewUser() {
        throw new ClientErrorException("Not yet supported.", Response.Status.BAD_REQUEST);
    }

}
