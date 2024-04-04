package dishesandusers.api.services;

import dishesandusers.api.domain.dish.Dish;
import dishesandusers.api.domain.dish.DishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    private DishService dishService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dishService = new DishService(dishRepository);
    }

    @Test
    public void addDishShouldReturnJsonOfNewDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.addDish(anyString(), anyDouble(), anyString())).thenReturn(dish);

        String result = dishService.addDish("Pizza", 9.99, "Delicious cheese pizza");

        assertNotNull(result);
        assertTrue(result.contains("\"id\":1"));
        assertTrue(result.contains("\"name\":\"Pizza\""));
        assertTrue(result.contains("\"price\":9.99"));
        assertTrue(result.contains("\"description\":\"Delicious cheese pizza\""));
    }

    @Test
    public void getAllDishesShouldReturnJsonOfDishList() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1, "Pizza", 9.99, "Delicious cheese pizza"));
        dishes.add(new Dish(2, "Burger", 12.99, "Delicious beef burger"));
        when(dishRepository.getAllDishes()).thenReturn(dishes);

        String result = dishService.getAllDishes();

        assertNotNull(result);
        assertTrue(result.contains("\"id\":1"));
        assertTrue(result.contains("\"name\":\"Pizza\""));
        assertTrue(result.contains("\"price\":9.99"));
        assertTrue(result.contains("\"description\":\"Delicious cheese pizza\""));
        assertTrue(result.contains("\"id\":2"));
        assertTrue(result.contains("\"name\":\"Burger\""));
        assertTrue(result.contains("\"price\":12.99"));
        assertTrue(result.contains("\"description\":\"Delicious beef burger\""));
    }

    @Test
    public void getDishByIdShouldReturnJsonOfDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.getDishById(anyInt())).thenReturn(dish);

        String result = dishService.getDishById(1);

        assertNotNull(result);
        assertTrue(result.contains("\"id\":1"));
        assertTrue(result.contains("\"name\":\"Pizza\""));
        assertTrue(result.contains("\"price\":9.99"));
        assertTrue(result.contains("\"description\":\"Delicious cheese pizza\""));
    }

    @Test
    public void updateDishShouldReturnJsonOfUpdatedDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.updateDish(anyInt(), anyString(), anyDouble(), anyString())).thenReturn(dish);

        String result = dishService.updateDish(1, "Burger", 12.99, "Delicious beef burger");

        assertNotNull(result);
        assertTrue(result.contains("\"id\":1"));
        assertTrue(result.contains("\"name\":\"Pizza\""));
        assertTrue(result.contains("\"price\":9.99"));
        assertTrue(result.contains("\"description\":\"Delicious cheese pizza\""));
    }

    @Test
    public void deleteDishShouldReturnTrueWhenDishExists() {
        when(dishRepository.deleteDishById(anyInt())).thenReturn(true);

        boolean result = dishService.deleteDish(1);

        assertTrue(result);
    }

    @Test
    public void deleteDishShouldReturnFalseWhenDishDoesNotExist() {
        when(dishRepository.deleteDishById(anyInt())).thenReturn(false);

        boolean result = dishService.deleteDish(1);

        assertFalse(result);
    }
}
