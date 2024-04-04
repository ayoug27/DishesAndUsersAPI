package dishesandusers.api.resources;

import dishesandusers.api.domain.user.UserRepository;
import dishesandusers.api.services.UserService;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

/**
 * UserResource provides endpoints for user management.
 */
@Path("/user")
public class UserResource extends AuthenticationAccess {
    /** UserService for user management operations. */
    private UserService service;

    /**
     * Default constructor.
     */
    public UserResource() {}

    /**
     * Constructor with UserRepository parameter.
     *
     * @param repository The UserRepository implementation.
     */
    @Inject
    public UserResource(UserRepository repository) {
        super(repository);
        this.service = new UserService(repository);
    }

    /**
     * Endpoint for adding a new user.
     *
     * @param json The JSON object containing user data.
     * @return A response containing the result of adding the user.
     */
    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUser(JsonObject json) {
        try {
            String username = json.getString("username");
            String password = json.getString("password");
            return Response.ok(service.addUser(username,password)).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Endpoint for retrieving all users.
     *
     * @return A JSON string representing all users.
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsers();
    }

    /**
     * Endpoint for retrieving a user by their id.
     *
     * @param id The id of the user to retrieve.
     * @return A response containing the user with the specified id.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) {
        try {
            return Response.ok(service.getUserById(id)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint for updating a user's information.
     *
     * @param requestContext The request context.
     * @param id The id of the user to update.
     * @param json The JSON object containing updated user data.
     * @return A response containing the result of updating the user.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@Context ContainerRequestContext requestContext, @PathParam("id") int id, JsonObject json) throws UnsupportedEncodingException {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }
        // authentification
        String authenticatedUser = checkAuthorization(authHeader);

        if(authenticatedUser != null) {
            try {
                String username = json.getString("username");
                String password = json.getString("password");
                return Response.ok(service.updateUser(id, username, password)).build();
            } catch (IllegalArgumentException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Endpoint for deleting a user.
     *
     * @param requestContext The request context.
     * @param id The id of the user to delete.
     * @return A response containing the result of deleting the user.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    @DELETE
    @Path("{id}")
    public Response deleteUser(@Context ContainerRequestContext requestContext, @PathParam("id") int id) throws UnsupportedEncodingException {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }
        // authentification
        String authenticatedUser = checkAuthorization(authHeader);

        if(authenticatedUser != null) {
            if (service.deleteUser(id))
                return Response.ok("User deleted").build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
