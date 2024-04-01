package dishesandusers.api.data;

import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;
import java.util.ArrayList;

public class MockDishRepository implements DishRepository {
    private final ArrayList<Dish> dishes;

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

    @Override
    public Dish addDish(String name, double price, String description) {
        Dish dishToAdd = new Dish(dishes.size(), name, price, description);
        return dishes.add(dishToAdd) ? dishToAdd : null;
    }

    @Override
    public ArrayList<Dish> getAllDishes() {
        return dishes;
    }

    @Override
    public Dish getDishById(int id) {
        return dishes.stream().filter(dish -> dish.getId() == id).findFirst().orElse(null);
    }

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

    @Override
    public boolean deleteDishById(int id) {
        return dishes.removeIf(dish -> dish.getId() == id);
    }
}
