package dishesandusers.api.resources;

import dishesandusers.api.domain.dish.DishRepository;
import dishesandusers.api.domain.user.UserRepository;
import dishesandusers.api.services.DishService;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;

/**
 * DishResource provides RESTful endpoints for managing Dish entities.
 */
@Path("/dish")
public class DishResource extends AuthenticationAccess {
    /** The service for managing Dish entities. */
    private DishService service;


    /** Default constructor. */
    public DishResource() {}

    @Inject
    public DishResource(DishRepository dishRepository, UserRepository userRepository) {
        super(userRepository);
        this.service = new DishService(dishRepository);
    }

    /**
     * Adds a new dish.
     *
     * @param json The JSON representation of the dish.
     * @return A Response indicating the result of the operation.
     */
    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addDish(@Context ContainerRequestContext requestContext, JsonObject json) throws UnsupportedEncodingException {
        // Récupération du header de la requête HTTP et
        // vérification de la présence des informations pour une authentification "basic"
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }
        // authentification
        String authenticatedUser = checkAuthorization(authHeader);

        if(authenticatedUser != null){
            try {
                String name = json.getString("name");
                double price = json.getJsonNumber("price").doubleValue();
                String description = json.getString("description");
                return Response.ok(service.addDish(name, price, description)).build();
            } catch (RuntimeException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Retrieves all dishes.
     *
     * @return A JSON representation of all dishes.
     */
    @GET
    @Produces("application/json")
    public String getAllDishes() {
        return service.getAllDishes();
    }

    /**
     * Retrieves a dish by ID.
     *
     * @param id The ID of the dish to retrieve.
     * @return A Response containing the dish if found, or an error response if not found.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getDishById(@PathParam("id") int id) {
        try {
            return Response.ok(service.getDishById(id)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Updates a dish.
     *
     * @param json The JSON representation of the updated dish.
     * @param id   The ID of the dish to update.
     * @return A Response indicating the result of the operation.
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateDish(@Context ContainerRequestContext requestContext, JsonObject json, @PathParam("id") int id) throws UnsupportedEncodingException {
        // Récupération du header de la requête HTTP et
        // vérification de la présence des informations pour une authentification "basic"
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        String authenticatedUser = checkAuthorization(authHeader);

        if(authenticatedUser != null) {
            try {
                String name = json.getString("name");
                double price = json.getJsonNumber("price").doubleValue();
                String description = json.getString("description");
                return Response.ok(service.updateDish(id, name, price, description)).build();
            } catch (IllegalArgumentException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Deletes a dish by ID.
     *
     * @param id The ID of the dish to delete.
     * @return A Response indicating the result of the operation.
     */
    @DELETE
    @Path("{id}")
    public Response deleteDish(@Context ContainerRequestContext requestContext, @PathParam("id") int id) throws UnsupportedEncodingException {
        // Récupération du header de la requête HTTP et
        // vérification de la présence des informations pour une authentification "basic"
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        // authentification
        String authenticatedUser = checkAuthorization(authHeader);

        if(authenticatedUser != null) {
            if (service.deleteDish(id))
                return Response.ok("Dish deleted").build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
