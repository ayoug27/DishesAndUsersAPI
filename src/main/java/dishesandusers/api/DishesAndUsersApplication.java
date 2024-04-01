package dishesandusers.api;

import dishesandusers.api.data.MockDishRepository;
import dishesandusers.api.domain.dish.DishRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class DishesAndUsersApplication extends Application {
    @Produces
    private DishRepository dishRepository = new MockDishRepository();
}