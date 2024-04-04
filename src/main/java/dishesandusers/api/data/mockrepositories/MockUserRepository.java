package dishesandusers.api.data.mockrepositories;

import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.Date;

public class MockUserRepository implements UserRepository {
    private final ArrayList<User> users;

    public MockUserRepository() {
        users = new ArrayList<>();

        User user1 = new User(0, "zizi", "zizi", new Date());

        users.add(user1);
    }

    @Override
    public User addUser(String username, String password) {
        return null;
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
     * @param name     The name of the user.
     * @param password The password of the user.
     * @return The User object if found, null otherwise.
     */
    @Override
    public User getUserByCredentials(String name, String password) {
        return users.stream().filter(user -> user.getUsername().equals(name) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User updateUser(int id, String name, String password) {
        return null;
    }

    /**
     * Deletes a user by their id.
     *
     * @param id The id of the user to be deleted.
     * @return true if the user was deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteUserById(int id) {
        return false;
    }
}
