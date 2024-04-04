package dishesandusers.api.domain.user;

import java.util.ArrayList;

/**
 * UserRepository interface represents the contract for user repository.
 * It defines the methods that any class implementing this interface should provide.
 */
public interface UserRepository {
    /**
     * Adds a new user to the repository.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object representing the added user.
     */
    User addUser(String username, String password);

    /**
     * Retrieves all users from the repository.
     *
     * @return An ArrayList of all users.
     */
    ArrayList<User> getAllUsers();

    /**
     * Retrieves a user by their id.
     *
     * @param id The id of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    User getUserById(int id);

    /**
     * Retrieves a user by their credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object if found, null otherwise.
     */
    User getUserByCredentials(String username, String password);

    /**
     * Updates user information in the repository.
     *
     * @param id       The id of the user to be updated.
     * @param username The new username of the user.
     * @param password The new password of the user.
     * @return The updated User object if successful, null otherwise.
     */
    User updateUser(int id, String username, String password);

    /**
     * Deletes a user by their id.
     *
     * @param id The id of the user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    boolean deleteUserById(int id);
}
