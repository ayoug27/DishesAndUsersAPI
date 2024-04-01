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
     * @param user The user to be added.
     * @return true if the user was added successfully, false otherwise.
     */
    boolean addUser(User user);

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

    /**
     * Updates a user in the repository.
     *
     * @param user The user to be updated.
     * @return true if the user was updated successfully, false otherwise.
     */
    boolean updateUser(User user);

    /**
     * Deletes a user by their id.
     *
     * @param id The id of the user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    boolean deleteUserById(int id);

    /**
     * Deletes a user from the repository.
     *
     * @param user The user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    boolean deleteUser(User user);
}
