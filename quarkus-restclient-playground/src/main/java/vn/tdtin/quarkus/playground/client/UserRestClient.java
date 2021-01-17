package vn.tdtin.quarkus.playground.client;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import vn.tdtin.quarkus.playground.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import java.util.Set;

@RegisterRestClient(configKey = "user-api")
public interface UserRestClient {

    /**
     * Call user service and returns {@link javax.ws.rs.core.Response}
     *
     * @return
     */
    @GET
    Response parseAsResponse();

    /**
     * call user service and parse response as {@link vn.tdtin.quarkus.playground.User} entity
     *
     * @return list of User
     */
    @GET
    Set<User> parseAsEntity();

    /**
     * simply call service without parsing response
     */
    @GET
    void ping();

    /**
     * Call user service with status code 400 returned.
     * @return Response
     */
    @POST
    Response callWithException();
}
