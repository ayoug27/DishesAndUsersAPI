package dishesandusers.api.domain.dish;

import java.util.ArrayList;

/**
 * The DishRepository interface represents the contract for a dish repository.
 * It defines the methods that any class implementing this interface should provide.
 * This interface is part of the domain layer of the application.
 */
public interface DishRepository {

    /**
     * Adds a new dish to the repository.
     *
     * @param name The name of the dish to be added.
     * @param price The price of the dish to be added.
     * @param description The description of the dish to be added.
     * @return The Dish object that was added.
     */
    Dish addDish(String name, double price, String description);

    /**
     * Retrieves all dishes from the repository.
     *
     * @return An ArrayList of all dishes. Each element is a Dish object. If no dishes are found, an empty list is returned.
     */
    ArrayList<Dish> getAllDishes();

    /**
     * Retrieves a dish by their id.
     *
     * @param id The id of the dish to retrieve. This should be a valid integer id.
     * @return The Dish object if found, null otherwise.
     */
    Dish getDishById(int id);

    /**
     * Updates a dish in the repository.
     *
     * @param id The id of the dish to be updated. This should be a valid integer id.
     * @param name The new name of the dish.
     * @param price The new price of the dish.
     * @param description The new description of the dish.
     * @return The Dish object that was updated.
     */
    Dish updateDish(int id, String name, double price, String description);

    /**
     * Deletes a dish by their id.
     *
     * @param id The id of the dish to be deleted. This should be a valid integer id.
     * @return true if the dish was deleted successfully, false otherwise.
     */
    boolean deleteDishById(int id);
}