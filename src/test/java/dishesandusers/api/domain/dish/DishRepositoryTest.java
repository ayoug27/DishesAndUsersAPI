package dishesandusers.api.domain.dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DishRepositoryTest {

    @Mock
    private DishRepository dishRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addingDishShouldReturnNewDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.addDish(anyString(), anyDouble(), anyString())).thenReturn(dish);

        Dish result = dishRepository.addDish("Pizza", 9.99, "Delicious cheese pizza");

        assertNotNull(result);
        assertEquals(dish, result);
    }

    @Test
    public void retrievingAllDishesShouldReturnListOfDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1, "Pizza", 9.99, "Delicious cheese pizza"));
        dishes.add(new Dish(2, "Burger", 12.99, "Delicious beef burger"));
        when(dishRepository.getAllDishes()).thenReturn(dishes);

        ArrayList<Dish> result = dishRepository.getAllDishes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(dishes, result);
    }

    @Test
    public void retrievingDishByIdShouldReturnDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.getDishById(anyInt())).thenReturn(dish);

        Dish result = dishRepository.getDishById(1);

        assertNotNull(result);
        assertEquals(dish, result);
    }

    @Test
    public void updatingDishShouldReturnUpdatedDish() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        when(dishRepository.updateDish(anyInt(), anyString(), anyDouble(), anyString())).thenReturn(dish);

        Dish result = dishRepository.updateDish(1, "Burger", 12.99, "Delicious beef burger");

        assertNotNull(result);
        assertEquals(dish, result);
    }

    @Test
    public void deletingDishByIdShouldReturnTrue() {
        when(dishRepository.deleteDishById(anyInt())).thenReturn(true);

        boolean result = dishRepository.deleteDishById(1);

        assertTrue(result);
    }
}