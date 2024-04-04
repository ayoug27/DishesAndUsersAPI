package dishesandusers.api;

import dishesandusers.api.data.mariadbrepositories.MariaDbDishRepository;
import dishesandusers.api.data.mariadbrepositories.MariaDbUserRepository;
import dishesandusers.api.domain.dish.DishRepository;
import dishesandusers.api.domain.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * DishesAndUsersApplication is the main application class for managing resources and database connections.
 */
@ApplicationPath("")
@ApplicationScoped
public class DishesAndUsersApplication extends Application {

    /**
     * Produces a DishRepository instance.
     *
     * @return A DishRepository instance.
     */
    @Produces
    private DishRepository openDishDatabaseConnection() {
        MariaDbDishRepository repository = null;

        try {
            repository = new MariaDbDishRepository("jdbc:mariadb://mysql-espace-virtuel-ayoub.alwaysdata.net/espace-virtuel-ayoub_dishesandusers", "351941", "cambodgeCITY");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return repository;
    }

    /**
     * Produces a UserRepository instance.
     *
     * @return A UserRepository instance.
     */
    @Produces
    private UserRepository openUserDatabaseConnection() {
        MariaDbUserRepository repository = null;

        try {
            repository = new MariaDbUserRepository("jdbc:mariadb://mysql-espace-virtuel-ayoub.alwaysdata.net/espace-virtuel-ayoub_dishesandusers", "351941", "cambodgeCITY");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return repository;
    }

    /**
     * Closes the DishRepository instance.
     *
     * @param repository The DishRepository instance to close.
     */
    private void closeDishDatabaseConnection(@Disposes DishRepository repository) {
        if (repository != null) {
            try {
                ((MariaDbDishRepository) repository).close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Closes the UserRepository instance.
     *
     * @param repository The DishRepository instance to close.
     */
    private void closeUserDatabaseConnection(@Disposes UserRepository repository) {
        if (repository != null) {
            try {
                ((MariaDbUserRepository) repository).close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}