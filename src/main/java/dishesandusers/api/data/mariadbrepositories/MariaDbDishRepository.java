package dishesandusers.api.data.mariadbrepositories;

import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * MariaDbDishRepository provides implementation for interacting with a MariaDB database
 * to manage Dish entities.
 */
public class MariaDbDishRepository extends MariaDbRepository implements DishRepository, Closeable {

    /**
     * Constructs a new MariaDbDishRepository with the specified authentication information.
     *
     * @param authenticationInfo The authentication information for connecting to the MariaDB database.
     * @param user               The username for authentication.
     * @param password           The password for authentication.
     * @throws SQLException           If a database access error occurs or this method is called on a closed connection.
     * @throws ClassNotFoundException If the driver class cannot be found.
     */
    public MariaDbDishRepository(String authenticationInfo, String user, String password ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        super(authenticationInfo, user, password);
    }

    @Override
    public Dish addDish(String name, double price, String description) {
         Dish dish = null;

         String insertQuery = "INSERT INTO Dish (name, price, description) VALUES (?, ?, ?);";
         String lastIdQuery =  "SELECT * FROM Dish WHERE id = LAST_INSERT_ID()";

         System.out.println("test");
         try (
                 PreparedStatement insertStatement = databaseConnection.prepareStatement(insertQuery);
                 PreparedStatement getLastIdStatement = databaseConnection.prepareStatement(lastIdQuery)
         ) {
             insertStatement.setString(1, name);
             insertStatement.setDouble(2, price);
             insertStatement.setString(3, description);

             insertStatement.executeQuery();
             ResultSet result = getLastIdStatement.executeQuery();

             if (result.next()) {
                 int id = result.getInt("id");
                 dish = new Dish(id, name, price, description);
             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

         return dish;
    }

    @Override
    public ArrayList<Dish> getAllDishes() {
        ArrayList<Dish> dishes;

        String query = "SELECT * FROM Dish";

        try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
            ResultSet result = statement.executeQuery();

            dishes = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double price = result.getDouble("price");
                String description = result.getString("description");

                Dish dish = new Dish(id, name, price, description);
                dishes.add(dish);
            }
        }
            catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dishes;
    }

    @Override
    public Dish getDishById(int id) {
        Dish dish = null;

        String query = "SELECT * FROM Dish WHERE id = ?";

        try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                double price = result.getDouble("price");
                String description = result.getString("description");

                dish = new Dish(id, name, price, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dish;
    }

    @Override
    public Dish updateDish(int id, String name, double price, String description) {
        Dish dish = null;

        String query = "UPDATE Dish SET name = ?, price = ?, description = ? WHERE id = ?;";

        try ( PreparedStatement statement = databaseConnection.prepareStatement(query) ) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setInt(4, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 1) {
                dish = new Dish(id, name, price, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dish;
    }

    @Override
    public boolean deleteDishById(int id) {
        boolean result = false;

        String query = "DELETE FROM Dish WHERE id = ?;";

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
