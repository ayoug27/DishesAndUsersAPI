package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

/**
 * AuthService provides authentication-related services.
 */
public class AuthService {
    /** Gson object for JSON serialization and deserialization. */
    private final Gson gson = new Gson();

    /** UserRepository for accessing user data. */
    protected UserRepository userRepository;

    /**
     * Constructor for AuthService.
     *
     * @param userRepository The UserRepository implementation.
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checks if a user with the given credentials exists.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A JSON string representing the user if found, null otherwise.
     */
    public String isUser(String username, String password) {
        User user = userRepository.getUserByCredentials(username, password);

        return user != null ? gson.toJson(user) : null;
    }
}
