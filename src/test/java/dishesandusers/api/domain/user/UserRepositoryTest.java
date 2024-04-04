package dishesandusers.api.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addingUserShouldReturnNewUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.addUser(anyString(), anyString())).thenReturn(user);

        User result = userRepository.addUser("testUser", "testPassword");

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void retrievingAllUsersShouldReturnListOfUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "testUser1", "testPassword1", new Date()));
        users.add(new User(2, "testUser2", "testPassword2", new Date()));
        when(userRepository.getAllUsers()).thenReturn(users);

        ArrayList<User> result = userRepository.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(users, result);
    }

    @Test
    public void retrievingUserByIdShouldReturnUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.getUserById(anyInt())).thenReturn(user);

        User result = userRepository.getUserById(1);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void retrievingUserByCredentialsShouldReturnUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.getUserByCredentials(anyString(), anyString())).thenReturn(user);

        User result = userRepository.getUserByCredentials("testUser", "testPassword");

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void updatingUserShouldReturnUpdatedUser() {
        User user = new User(1, "testUser", "testPassword", new Date());
        when(userRepository.updateUser(anyInt(), anyString(), anyString())).thenReturn(user);

        User result = userRepository.updateUser(1, "newUser", "newPassword");

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    public void deletingUserByIdShouldReturnTrue() {
        when(userRepository.deleteUserById(anyInt())).thenReturn(true);

        boolean result = userRepository.deleteUserById(1);

        assertTrue(result);
    }
}
