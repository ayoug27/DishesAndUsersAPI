package dishesandusers.api.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1, "testUser", "testPassword", new Date());
    }

    @Test
    public void creatingUserShouldSetPropertiesCorrectly() {
        assertEquals(1, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertFalse(user.isAdmin());
    }

    @Test
    public void updatingUsernameShouldChangeUsernameProperty() {
        user.setUsername("newUsername");

        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void updatingPasswordShouldChangePasswordProperty() {
        user.setPassword("newPassword");

        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void settingAdminShouldChangeAdminProperty() {
        user.setAdmin(true);

        assertTrue(user.isAdmin());
    }
}
