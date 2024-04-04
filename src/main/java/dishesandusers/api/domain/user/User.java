package dishesandusers.api.domain.user;

import com.google.javascript.jscomp.jarjar.com.google.gson.annotations.SerializedName;

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
    private transient String password; // Password of the user

    /** Flag indicating if the user is an admin */
    @SerializedName("is_admin")
    private boolean admin;

    /** Date when the user was created */
    @SerializedName("created_at")
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
        this.admin = false;
        this.createdAt = createdAt;
    }

    /**
     * Constructor for the User class with admin flag.
     *
     * @param id        Unique identifier for the user
     * @param username  Username of the user
     * @param password  Password of the user
     * @param admin     Flag indicating if the user is an admin
     * @param createdAt Date when the user was created
     */
    public User(int id, String username, String password, boolean admin, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
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

    /**
     * Returns the password of the user.
     *
     * @return password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id id of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the username of the user.
     *
     * @param username username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password password of the user
     */
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

    /**
     * Checks if the user is an admin.
     *
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin true if the user is an admin, false otherwise
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
