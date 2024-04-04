package dishesandusers.api.resources;

import dishesandusers.api.domain.user.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

/**
 * AuthResource provides endpoints for user authentication.
 */
@Path("/auth")
public class AuthResource extends AuthenticationAccess {

    /**
     * Default constructor.
     */
    public AuthResource() {}

    /**
     * Constructor with UserRepository parameter.
     *
     * @param repository The UserRepository implementation.
     */
    public @Inject AuthResource(UserRepository repository) {
        super(repository);
    }

    /**
     * Endpoint for user authentication.
     *
     * @param requestContext The request context.
     * @return A response containing the authentication result.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    @GET
    @Produces("application/json")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {
        // Retrieving the HTTP request header and checking for basic authentication information
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // Sending an error response with the appropriate protocol to use
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        String authenticatedUser = checkAuthorization(authHeader);

        // Sending a response with the authentication value
        return authenticatedUser != null ? Response.ok(authenticatedUser).build() : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
