package dishesandusers.api.domain.dish;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishTest {

    @Test
    public void creatingDishShouldSetPropertiesCorrectly() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");

        assertEquals(1, dish.getId());
        assertEquals("Pizza", dish.getName());
        assertEquals(9.99, dish.getPrice());
        assertEquals("Delicious cheese pizza", dish.getDescription());
    }

    @Test
    public void updatingNameShouldChangeNameProperty() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        dish.setName("Burger");

        assertEquals("Burger", dish.getName());
    }

    @Test
    public void updatingPriceShouldChangePriceProperty() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        dish.setPrice(12.99);

        assertEquals(12.99, dish.getPrice());
    }

    @Test
    public void updatingDescriptionShouldChangeDescriptionProperty() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");
        dish.setDescription("Delicious beef burger");

        assertEquals("Delicious beef burger", dish.getDescription());
    }

    @Test
    public void toStringShouldReturnCorrectRepresentation() {
        Dish dish = new Dish(1, "Pizza", 9.99, "Delicious cheese pizza");

        String expected = "Dish{id=1, name='Pizza', price=9.99, description='Delicious cheese pizza'}";
        assertEquals(expected, dish.toString());
    }
}
