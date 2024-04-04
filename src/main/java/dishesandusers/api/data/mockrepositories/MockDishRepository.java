package dishesandusers.api.data.mockrepositories;

import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;
import java.util.ArrayList;

/**
 * MockDishRepository is a mock implementation of the DishRepository interface,
 * providing methods to manage a collection of Dish objects for testing or
 * demonstration purposes.
 */
public class MockDishRepository implements DishRepository {
    /** The list of dishes stored in this repository. */
    private final ArrayList<Dish> dishes;

    /**
     * Constructs a new MockDishRepository with an empty list of dishes.
     * Pre-made dishes are added to the list for demonstration purposes.
     */
    public MockDishRepository() {
        dishes = new ArrayList<>();

        // Pre-made dishes
        Dish pizza = new Dish(0, "Pizza", 10.99, "Délicieuse pizza au fromage");
        Dish burger = new Dish(1, "Burger", 8.99, "Burger au boeuf juteux");
        Dish pasta = new Dish(2, "Pâtes", 9.99, "Pâtes à la mode italienne");
        Dish roastChicken = new Dish(3, "Poulet rôti", 12.99, "Poulet rôti croustillant");
        Dish caesarSalad = new Dish(4, "Salade César", 7.99, "Salade César fraîche et croquante");

        dishes.add(pizza);
        dishes.add(burger);
        dishes.add(pasta);
        dishes.add(roastChicken);
        dishes.add(caesarSalad);
    }

    /**
     * Adds a new dish to the repository.
     *
     * @param name        The name of the dish.
     * @param price       The price of the dish.
     * @param description The description of the dish.
     * @return The added Dish object, or null if the addition fails.
     */
    @Override
    public Dish addDish(String name, double price, String description) {
        Dish dishToAdd = new Dish(dishes.size(), name, price, description);
        return dishes.add(dishToAdd) ? dishToAdd : null;
    }

    /**
     * Retrieves all dishes stored in the repository.
     *
     * @return An ArrayList containing all dishes stored in the repository.
     */
    @Override
    public ArrayList<Dish> getAllDishes() {
        return dishes;
    }

    /**
     * Retrieves a dish from the repository based on its ID.
     *
     * @param id The ID of the dish to retrieve.
     * @return The Dish object with the specified ID, or null if not found.
     */
    @Override
    public Dish getDishById(int id) {
        return dishes.stream().filter(dish -> dish.getId() == id).findFirst().orElse(null);
    }

    /**
     * Updates the details of a dish in the repository.
     *
     * @param id          The ID of the dish to update.
     * @param name        The new name of the dish.
     * @param price       The new price of the dish.
     * @param description The new description of the dish.
     * @return The updated Dish object, or null if the dish with the specified ID was not found.
     */
    @Override
    public Dish updateDish(int id, String name, double price, String description) {
        Dish dishToUpdate = dishes.stream().filter(dish -> dish.getId() == id).findFirst().orElse(null);
        if (dishToUpdate != null) {
            dishToUpdate.setName(name);
            dishToUpdate.setPrice(price);
            dishToUpdate.setDescription(description);
        }

        return dishToUpdate;
    }

    /**
     * Deletes a dish from the repository based on its ID.
     *
     * @param id The ID of the dish to delete.
     * @return true if the dish was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteDishById(int id) {
        return dishes.removeIf(dish -> dish.getId() == id);
    }
}
