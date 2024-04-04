package dishesandusers.api.data.mariadbrepositories;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MariaDbRepository is an abstract class representing a repository for MariaDB database.
 * It provides functionality for managing database connections.
 */
public abstract class MariaDbRepository implements Closeable {
    /** The database connection object. */
    protected Connection databaseConnection;

    /**
     * Constructor for MariaDbRepository.
     *
     * @param authenticationInfo The authentication information for connecting to the database.
     * @param user               The username for database authentication.
     * @param password           The password for database authentication.
     * @throws java.sql.SQLException     If a database access error occurs.
     * @throws java.lang.ClassNotFoundException If the driver class is not found.
     */
    public MariaDbRepository(String authenticationInfo, String user, String password) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        databaseConnection = DriverManager.getConnection(authenticationInfo, user, password);
    }

    /**
     * Closes the database connection.
     *
     * @throws IOException If an I/O error occurs while closing the connection.
     */
    @Override
    public void close() throws IOException {
        try {
            databaseConnection.close();
        } catch (java.sql.SQLException e) {
            throw new IOException(e);
        }
    }
}