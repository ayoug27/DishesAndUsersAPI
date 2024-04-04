package dishesandusers.api.data.mariadbrepositories;

import dishesandusers.api.domain.user.User;
import dishesandusers.api.domain.user.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * MariaDbUserRepository provides implementation for interacting with a MariaDB database
 * to manage User entities.
 */
public class MariaDbUserRepository extends MariaDbRepository implements UserRepository {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a new MariaDbUserRepository with the specified authentication information.
     *
     * @param authenticationInfo The authentication information for connecting to the MariaDB database.
     * @param user               The username for authentication.
     * @param password           The password for authentication.
     * @throws SQLException           If a database access error occurs or this method is called on a closed connection.
     * @throws ClassNotFoundException If the driver class cannot be found.
     */
    public MariaDbUserRepository(String authenticationInfo, String user, String password) throws SQLException, ClassNotFoundException {
        super(authenticationInfo, user, password);
    }

        @Override
        public User addUser(String username, String password) {
            User user = null;

            String insertQuery = "INSERT INTO User (username, password) VALUES (?, ?)";
            String lastIdQuery = "SELECT * FROM User WHERE id = LAST_INSERT_ID()";

            try ( PreparedStatement insertStatement = databaseConnection.prepareStatement(insertQuery);
                  PreparedStatement getLastIdStatement = databaseConnection.prepareStatement(lastIdQuery) ) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));

                insertStatement.executeQuery();
                ResultSet result = getLastIdStatement.executeQuery();

                if (result.next()) {
                    int id = result.getInt("id");
                    user = new User(id, username, password, new Date());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return user;
        }

        /**
         * Retrieves all users from the repository.
         *
         * @return An ArrayList of all users.
         */
        @Override
        public ArrayList<User> getAllUsers() {
            ArrayList<User> users;

            String query = "SELECT * FROM User";

            try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
                ResultSet result = statement.executeQuery();

                users = new ArrayList<>();

                while (result.next()) {
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String createdAt = result.getString("created_at");

                    users.add(new User(id, username, password, dateFormat.parse(createdAt)));
                }
            }
            catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }

            return users;
        }

        /**
         * Retrieves a user by their id.
         *
         * @param id The id of the user to retrieve.
         * @return The User object if found, null otherwise.
         */
        @Override
        public User getUserById(int id) {
            User user = null;

            String query = "SELECT * FROM User WHERE id = ?";

            try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
                statement.setInt(1, id);

                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String createdAt = result.getString("created_at");

                    user = new User(id, username, password, dateFormat.parse(createdAt));
                }
            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }

            return user;
        }

        /**
         * Retrieves a user by their credentials.
         *
         * @param name     The name of the user.
         * @param password The password of the user.
         * @return The User object if found, null otherwise.
         */
        @Override
        public User getUserByCredentials(String name, String password) {
            User user = null;

            String query = "SELECT * FROM User WHERE username = ?";

            try( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
                statement.setString(1, name);

                ResultSet result = statement.executeQuery();

                if(result.next()) {
                    int id = result.getInt("id");
                    String username = result.getString("username");
                    String hashedPassword = result.getString("password");
                    String createdAt = result.getString("created_at");

                    if(BCrypt.checkpw(password, hashedPassword))
                        user = new User(id, username, hashedPassword, dateFormat.parse(createdAt));
                }
            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }
            return user;
        }

        @Override
        public User updateUser(int id, String name, String password) {
            User user = null;

            String query = "UPDATE User SET username = ?, password = ? WHERE id = ?";

            try ( PreparedStatement statement = databaseConnection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                statement.setInt(3, id);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 1) {
                    user = new User(id, name, password, new Date());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return user;
        }

        /**
         * Deletes a user by their id.
         *
         * @param id The id of the user to be deleted.
         * @return true if the user was deleted successfully, false otherwise.
         */
        @Override
        public boolean deleteUserById(int id) {
            boolean result = false;

            String query = "DELETE FROM User WHERE id = ?";

            try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
                statement.setInt(1, id);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 1) result = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return result;
        }


}
