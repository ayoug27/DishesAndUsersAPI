package dishesandusers.api.services;

import com.google.javascript.jscomp.jarjar.com.google.gson.Gson;
import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;

public class DishService {
    private final Gson gson = new Gson();

    protected DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public String addDish(String name, double price, String description) {
        Dish result = dishRepository.addDish(name, price, description);
        if (result == null) {
            throw new RuntimeException("Failed to add dish");
        } else {
            return gson.toJson(result);
        }
    }

    public String getAllDishes() {
        return gson.toJson(dishRepository.getAllDishes());
    }

    public String getDishById(int id) {
        Dish dish = dishRepository.getDishById(id);
        if (dish == null) {
            throw new IllegalArgumentException("Dish not found");
        } else {
            return gson.toJson(dish);
        }
    }

    public String updateDish(int id, String name, double price, String description) {
        Dish result = dishRepository.updateDish(id, name, price, description);
        if (result == null) {
            throw new IllegalArgumentException("Dish not found");
        } else {
            return gson.toJson(result);
        }
    }

    public boolean deleteDish(int id) {
        return dishRepository.deleteDishById(id);
    }
}
