package vn.tdtin.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.jaxrs.internal.ClientResponse;
import org.jboss.resteasy.client.jaxrs.internal.proxy.extractors.DefaultEntityExtractorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vn.tdtin.quarkus.playground.User;
import vn.tdtin.quarkus.playground.client.UserRestClient;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.Set;

@QuarkusTest
public class UserRestClientTest {

    @Inject
    @RestClient
    UserRestClient userRestClient;

    /**
     * Should call and parse response successfully.
     * RestEasy will call {@link Response#readEntity(Class)} internally
     * when we define return type UserRestClient interface.
     * RestEasy will call {@link org.jboss.resteasy.client.jaxrs.internal.proxy.extractors.BodyEntityExtractor#extractEntity}
     * to parse the response, and {@link Response#close} will be call at the end.
     */
    @Test
    public void parseAsEntity_shouldCallAndParseEntitySuccessFully() {
        for (int i = 0; i < 100; i++) {
            System.out.println("index: " + i);
            Set<User> users = userRestClient.parseAsEntity();
            System.out.println(users);
        }

    }

    /**
     * Will be blocked because connection pool size by default is 50.
     * Because we didn't close the response after calling rest service,
     * so the connection pool will never be released.
     */
    @Test
    public void parseAsResponse_connectionShouldBeBlocked_when_notClosingResponse() {
        for (int i = 0; i < 100; i++) {
            System.out.println("index: " + i);
            Response response = userRestClient.parseAsResponse();
            //response.close();
            System.out.println(response);
        }

    }

    /**
     * with void method, RestEasy will use {@link DefaultEntityExtractorFactory#createVoidExtractor()}
     * to extract the response and {@link Response#close} will be call at the end
     */
    @Test
    public void ping_shouldFetchDataWithoutBlock() {
        for (int i = 0; i < 100; i++) {
            userRestClient.ping();
        }
    }

    /**
     * In case the return code is greater than 400 ( exception ),
     * If you are using MicroProfile, exception with be handle in
     * {@link org.jboss.resteasy.microprofile.client.ExceptionMapping.HandlerException#mapException(Method)},
     * at the end connection will be released by {@link ClientResponse#bufferEntity()}
     */
    @Test
    public void callWithException_shouldCloseConnectionPool_inCaseException() {
        for (int i = 0; i < 200; i++) {
            System.out.println("index: " + i);
            Assertions.assertThrows(WebApplicationException.class, () -> userRestClient.callWithException());
        }
    }


}