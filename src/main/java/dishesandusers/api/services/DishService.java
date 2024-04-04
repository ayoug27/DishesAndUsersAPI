package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;

/**
 * DishService provides methods for interacting with Dish entities through a DishRepository.
 */
public class DishService {
    /** Gson instance for JSON serialization. */
    private final Gson gson = new Gson();

    /** The repository for managing Dish entities. */
    protected DishRepository dishRepository;

    /**
     * Constructs a new DishService with the specified DishRepository.
     *
     * @param dishRepository The repository for managing Dish entities.
     */
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * Adds a new dish to the repository.
     *
     * @param name        The name of the dish.
     * @param price       The price of the dish.
     * @param description The description of the dish.
     * @return A JSON string representing the added Dish object.
     * @throws RuntimeException if the dish addition fails.
     */
    public String addDish(String name, double price, String description) {
        Dish result = dishRepository.addDish(name, price, description);
        if (result == null) {
            throw new RuntimeException("Failed to add dish");
        } else {
            return gson.toJson(result);
        }
    }

    /**
     * Retrieves all dishes from the repository.
     *
     * @return A JSON string representing the list of Dish objects.
     */
    public String getAllDishes() {
        return gson.toJson(dishRepository.getAllDishes());
    }

    /**
     * Retrieves a dish from the repository by its ID.
     *
     * @param id The ID of the dish to retrieve.
     * @return A JSON string representing the retrieved Dish object.
     * @throws IllegalArgumentException if the dish with the specified ID is not found.
     */
    public String getDishById(int id) {
        Dish dish = dishRepository.getDishById(id);
        if (dish == null)
            throw new IllegalArgumentException("Dish not found");
        else
            return gson.toJson(dish);
    }

    /**
     * Updates the details of a dish in the repository.
     *
     * @param id          The ID of the dish to update.
     * @param name        The new name of the dish.
     * @param price       The new price of the dish.
     * @param description The new description of the dish.
     * @return A JSON string representing the updated Dish object.
     * @throws IllegalArgumentException if the dish with the specified ID is not found.
     */
    public String updateDish(int id, String name, double price, String description) {
        Dish result = dishRepository.updateDish(id, name, price, description);
        if (result == null) {
            throw new IllegalArgumentException("Dish not found");
        } else {
            return gson.toJson(result);
        }
    }

    /**
     * Deletes a dish from the repository by its ID.
     *
     * @param id The ID of the dish to delete.
     * @return true if the dish was successfully deleted, false otherwise.
     */
    public boolean deleteDish(int id) {
        return dishRepository.deleteDishById(id);
    }
}
