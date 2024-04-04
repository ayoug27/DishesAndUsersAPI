package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final Gson gson = new Gson();

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void addUserShouldReturnJsonOfNewUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.addUser(anyString(), anyString())).thenReturn(user);

        String result = userService.addUser("testUser", "testPassword");

        assertNotNull(result);
        assertEquals(result,gson.toJson(user));
    }

    @Test
    public void getAllUsersShouldReturnJsonOfUserList() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "testUser1", "testPassword1", new Date()));
        users.add(new User(2, "testUser2", "testPassword2", new Date()));
        when(userRepository.getAllUsers()).thenReturn(users);

        String result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(result,gson.toJson(users));
    }

    @Test
    public void getUserByIdShouldReturnJsonOfUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.getUserById(anyInt())).thenReturn(user);

        String result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(result,gson.toJson(user));
    }

    @Test
    public void updateUserShouldReturnJsonOfUpdatedUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.updateUser(anyInt(), anyString(), anyString())).thenReturn(user);

        String result = userService.updateUser(1, "newUser", "newPassword");

        assertNotNull(result);
        assertEquals(result,gson.toJson(user));
    }

    @Test
    public void deleteUserShouldReturnTrueWhenUserExists() {
        when(userRepository.deleteUserById(anyInt())).thenReturn(true);

        boolean result = userService.deleteUser(1);

        assertTrue(result);
    }

    @Test
    public void deleteUserShouldReturnFalseWhenUserDoesNotExist() {
        when(userRepository.deleteUserById(anyInt())).thenReturn(false);

        boolean result = userService.deleteUser(1);

        assertFalse(result);
    }
}
