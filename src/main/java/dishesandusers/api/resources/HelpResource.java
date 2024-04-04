package dishesandusers.api.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/help")
public class HelpResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> listEndpoints() {
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("/dish/add - POST", "Adds a new dish. Requires basic authentication.");
        endpoints.put("/dish - GET", "Retrieves all dishes. No authentication required.");
        endpoints.put("/dish/{id} - GET", "Retrieves a specific dish by its ID. No authentication required.");
        endpoints.put("/dish/{id} - PUT", "Updates a specific dish by its ID. Requires basic authentication.");
        endpoints.put("/dish/{id} - DELETE", "Deletes a specific dish by its ID. Requires basic authentication.");
        endpoints.put("/user/add - POST", "Adds a new user. Requires basic authentication.");
        endpoints.put("/user - GET", "Retrieves all users. No authentication required.");
        endpoints.put("/user/{id} - GET", "Retrieves a specific user by its ID. No authentication required.");
        endpoints.put("/user/{id} - PUT", "Updates a specific user by its ID. Requires basic authentication.");
        endpoints.put("/user/{id} - DELETE", "Deletes a specific user by its ID. Requires basic authentication.");
        endpoints.put("/auth - GET", "Authenticates a user. Requires basic authentication.");
        return endpoints;
    }
}