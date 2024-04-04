package dishesandusers.api.data.mariadbrepositories;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class MariaDbRepository implements Closeable {
    /** The database connection object. */
    protected Connection databaseConnection;

    public MariaDbRepository(String authenticationInfo, String user, String password ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        databaseConnection = DriverManager.getConnection(authenticationInfo, user, password) ;
    }

    /**
     * Closes the database connection.
     *
     * @throws IOException If an I/O error occurs while closing the connection.
     */
    @Override
    public void close() throws IOException {
        try{
            databaseConnection.close();
        } catch (java.sql.SQLException e) {
            throw new IOException(e);
        }
    }
}
