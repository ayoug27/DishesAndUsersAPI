package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

/**
 * UserService provides operations related to user management.
 */
public class UserService {
    /** Gson instance for JSON serialization. */
    private final Gson gson = new Gson();

    /** UserRepository for accessing user data. */
    protected UserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository The UserRepository implementation.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new user.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @return A JSON string representing the newly added user.
     * @throws RuntimeException If failed to add the user.
     */
    public String addUser(String username, String password) {
        User result = userRepository.addUser(username, password);
        if (result == null) {
            throw new RuntimeException("Failed to add user");
        } else {
            return gson.toJson(result);
        }
    }

    /**
     * Retrieves all users.
     *
     * @return A JSON string representing all users.
     */
    public String getAllUsers() {
        return gson.toJson(userRepository.getAllUsers());
    }

    /**
     * Retrieves a user by their id.
     *
     * @param id The id of the user.
     * @return A JSON string representing the user with the given id.
     * @throws IllegalArgumentException If the user with the given id is not found.
     */
    public String getUserById(int id) {
        User user = userRepository.getUserById(id);
        if (user == null)
            throw new IllegalArgumentException("User with id " + id + " not found");
        else
            return gson.toJson(user);
    }

    /**
     * Updates a user's information.
     *
     * @param id       The id of the user to update.
     * @param username The new username for the user.
     * @param password The new password for the user.
     * @return A JSON string representing the updated user.
     * @throws IllegalArgumentException If the user with the given id is not found.
     */
    public String updateUser(int id, String username, String password) {
        User user = userRepository.updateUser(id, username, password);
        if (user == null)
            throw new IllegalArgumentException("User with id " + id + " not found");
        else
            return gson.toJson(user);
    }

    /**
     * Deletes a user.
     *
     * @param id The id of the user to delete.
     * @return true if the user was deleted successfully, false otherwise.
     */
    public boolean deleteUser(int id) {
        return userRepository.deleteUserById(id);
    }
}
