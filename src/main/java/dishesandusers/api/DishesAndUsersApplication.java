package dishesandusers.api;

import dishesandusers.api.data.mariadbrepositories.MariaDbDishRepository;
import dishesandusers.api.data.mariadbrepositories.MariaDbUserRepository;
import dishesandusers.api.domain.dish.DishRepository;
import dishesandusers.api.domain.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("")
@ApplicationScoped
public class DishesAndUsersApplication extends Application {
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
}