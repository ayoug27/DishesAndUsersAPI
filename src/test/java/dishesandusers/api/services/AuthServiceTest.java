package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(userRepository);
    }

    @Test
    public void isUserShouldReturnUserJsonWhenCredentialsAreValid() {
        User user = new User(1, "testUser", "testPassword", null);
        when(userRepository.getUserByCredentials(anyString(), anyString())).thenReturn(user);

        String result = authService.isUser("testUser", "testPassword");

        assertNotNull(result);
        assertEquals(result,new Gson().toJson(user));
    }

    @Test
    public void isUserShouldReturnNullWhenCredentialsAreInvalid() {
        when(userRepository.getUserByCredentials(anyString(), anyString())).thenReturn(null);

        String result = authService.isUser("invalidUser", "invalidPassword");

        assertNull(result);
    }
}
