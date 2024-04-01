package dishesandusers.api.domain.user;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Date;

/**
 * User class represents a user in the system.
 */
public class User {
    /** Unique identifier for the user */
    private int id;

    /** Username of the user */
    private String username;

    /** Password of the user */
    private String password; // Password of the user

    /** Date when the user was created */
    @JsonbProperty("created_at")
    private final Date createdAt; // Date when the user was created

    /**
     * Constructor for the User class.
     *
     * @param id        Unique identifier for the user
     * @param username  Username of the user
     * @param password  Password of the user
     * @param createdAt Date when the user was created
     */
    public User(int id, String username, String password, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the username of the user.
     *
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the creation date of the user.
     *
     * @return creation date of the user
     */
    public Date getCreatedAt() {
        return createdAt;
    }
}
