package dishesandusers.api.resources;

import dishesandusers.api.domain.user.UserRepository;
import dishesandusers.api.services.AuthService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AuthenticationAccess provides methods for authentication access.
 */
public abstract class AuthenticationAccess {
    /** AuthService for user authentication. */
    private AuthService auth;


    /**
     * Default constructor.
     */
    public AuthenticationAccess() {}

    /**
     * Constructor with UserRepository parameter.
     *
     * @param repository The UserRepository implementation.
     */
    public AuthenticationAccess(UserRepository repository) {
        this.auth = new AuthService(repository);
    }


    /**
     * Checks authorization based on the provided authentication header.
     *
     * @param authHeader The authentication header.
     * @return A JSON string representing the user if authorized, null otherwise.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    public String checkAuthorization(String authHeader) throws UnsupportedEncodingException {
        String[] tokens = (new String(Base64.getDecoder().decode(authHeader.split(" ")[1]), StandardCharsets.UTF_8)).split(":");
        final String username = tokens[0];
        final String pwd = tokens[1];

        return auth.isUser(username, pwd);
    }
}
