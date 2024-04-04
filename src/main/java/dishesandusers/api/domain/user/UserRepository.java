package dishesandusers.api.domain.user;

import java.util.ArrayList;

/**
 * UserRepository interface represents the contract for user repository.
 * It defines the methods that any class implementing this interface should provide.
 */
public interface UserRepository {
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
     * @param name The name of the user.
     * @param password The password of the user.
     * @return The User object if found, null otherwise.
     */
    User getUserByCredentials(String name, String password);


    User updateUser(int id, String name, String password);

    /**
     * Deletes a user by their id.
     *
     * @param id The id of the user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    boolean deleteUserById(int id);
}
