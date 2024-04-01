package dishesandusers.api.resources;

import dishesandusers.api.domain.dish.DishRepository;
import dishesandusers.api.services.DishService;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/dish")
public class DishResource {
    private DishService service;

    public DishResource() {}

    public @Inject DishResource(DishRepository repository) {
        this.service = new DishService(repository);
    }

    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addDish(JsonObject json) {
        try {
            String name = json.getString("name");
            double price = json.getJsonNumber("price").doubleValue();
            String description = json.getString("description");
            return Response.ok(service.addDish(name, price, description)).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces("application/json")
    public String getAllOrders() {
        return service.getAllDishes();
    }

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

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateDish(JsonObject json, @PathParam("id") int id) {
        try {
            String name = json.getString("name");
            double price = json.getJsonNumber("price").doubleValue();
            String description = json.getString("description");
            return Response.ok(service.updateDish(id, name, price, description)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Path("{id}")
    public Response deleteDish(@PathParam("id") int id) {
        if (service.deleteDish(id))
            return Response.ok("Dish deleted").build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
