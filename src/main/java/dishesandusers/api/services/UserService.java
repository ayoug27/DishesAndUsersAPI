package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

public class UserService {
    /** Gson instance for JSON serialization. */
    private final Gson gson = new Gson();

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String username, String password) {
        User result = userRepository.addUser(username, password);
        if (result == null) {
            throw new RuntimeException("Failed to add user");
        } else {
            return gson.toJson(result);
        }
    }

    public String getAllUsers() {
        return gson.toJson(userRepository.getAllUsers());
    }

    public String getUserById(int id) {
        User user = userRepository.getUserById(id);
        if (user == null)
            throw new IllegalArgumentException("User with id " + id + " not found");
        else
            return gson.toJson(user);
    }

    public String updateUser(int id, String username, String password) {
        User user = userRepository.updateUser(id, username, password);
        if (user == null)
            throw new IllegalArgumentException("User with id " + id + " not found");
        else
            return gson.toJson(user);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUserById(id);
    }
}
