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

@Path("/auth")
public class AuthResource extends AuthenticationAccess {
    public AuthResource() {}

    public @Inject AuthResource(UserRepository repository) {
        super(repository);
    }

    @GET
    @Produces("application/json")
    public Response authenticate(@Context ContainerRequestContext requestContext) throws UnsupportedEncodingException {

        // Récupération du header de la requête HTTP et
        // vérification de la présence des informations pour une authentification "basic"
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic")) {
            // envoie d'un code d'erreur avec dans l'en-tête le protocol à utiliser
            return Response.status(Response.Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic").build();
        }

        String authenticatedUser = checkAuthorization(authHeader);

        // envoie d'une réponse avec la valeur de l'authentification
        return authenticatedUser != null ? Response.ok(authenticatedUser).build() : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
