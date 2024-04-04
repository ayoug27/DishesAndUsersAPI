package dishesandusers.api.data.mockrepositories;

import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.Date;

/**
 * MockUserRepository is a mock implementation of UserRepository for testing purposes.
 * It provides hardcoded user data.
 */
public class MockUserRepository implements UserRepository {
    private final ArrayList<User> users;

    /**
     * Constructor for MockUserRepository.
     * Initializes the repository with some mock user data.
     */
    public MockUserRepository() {
        users = new ArrayList<>();

        User user1 = new User(0, "ayoug", "password", new Date());

        users.add(user1);
    }

    /**
     * Adds a new user to the repository (not implemented in mock).
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object representing the added user.
     */
    @Override
    public User addUser(String username, String password) {
        return null; // Not implemented in mock
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return An ArrayList of all users.
     */
    @Override
    public ArrayList<User> getAllUsers() {
        return users;
    }

    /**
     * Retrieves a user by their id.
     *
     * @param id The id of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    @Override
    public User getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    /**
     * Retrieves a user by their credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object if found, null otherwise.
     */
    @Override
    public User getUserByCredentials(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    /**
     * Updates user information in the repository (not implemented in mock).
     *
     * @param id       The id of the user to be updated.
     * @param username The new username of the user.
     * @param password The new password of the user.
     * @return The updated User object if successful, null otherwise.
     */
    @Override
    public User updateUser(int id, String username, String password) {
        return null; // Not implemented in mock
    }

    /**
     * Deletes a user by their id (not implemented in mock).
     *
     * @param id The id of the user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteUserById(int id) {
        return false; // Not implemented in mock
    }
}
