package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;

public class AuthService {
    private final Gson gson = new Gson();

    protected UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String isUser(String username, String password) {
        User user = userRepository.getUserByCredentials(username, password);

        return user != null ? gson.toJson(user) : null;
    }
}
