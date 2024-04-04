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

@Path("/user")
public class UserResource extends AuthenticationAccess {
    private UserService service;

    public UserResource() {}

    @Inject
    public UserResource(UserRepository repository) {
        super(repository);
        this.service = new UserService(repository);
    }

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

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsers();
    }

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
