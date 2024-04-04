package dishesandusers.api.resources;

import dishesandusers.api.domain.user.UserRepository;
import dishesandusers.api.services.AuthService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class AuthenticationAccess {
    private AuthService auth;

    public AuthenticationAccess() {}

    public AuthenticationAccess(UserRepository repository) {
        this.auth = new AuthService(repository);
    }

    public String checkAuthorization(String authHeader) throws UnsupportedEncodingException {
        String[] tokens = (new String(Base64.getDecoder().decode(authHeader.split(" ")[1]), StandardCharsets.UTF_8)).split(":");
        final String username = tokens[0];
        final String pwd = tokens[1];

        return auth.isUser(username, pwd);
    }
}
