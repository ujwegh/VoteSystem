package votesystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import votesystem.RestaurantTestData;
import votesystem.model.Meal;
import votesystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;

import static votesystem.MealTestData.*;
import static votesystem.RestaurantTestData.RESTAURANT_ID;
import static votesystem.RestaurantTestData.rest2;

public class MealServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("meals").clear();
    }

    @Test
    public void get() throws Exception {
        Meal actual = service.get(MEAL_ID, RESTAURANT_ID + 1);
        assertMatch(actual, meal1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1, RESTAURANT_ID + 1);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, RESTAURANT_ID + 1);
        assertMatch(service.getAll(RESTAURANT_ID + 1), sortByPrice(Arrays.asList(meal33, meal22, meal11, meal3, meal2)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MEAL_ID, 1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(RESTAURANT_ID), sortByPrice(shashlikMeals));
    }

    @Test
    public void getAllByDate() {
        assertMatch(service.getAllByDate(RESTAURANT_ID, LocalDate.now()), getByDate(shashlikMeals, LocalDate.now()));
    }

    @Test
    public void create() {
        Meal meal = new Meal("Новая шаурма", 222, LocalDate.now());
        service.create(meal, RESTAURANT_ID + 1);
        assertMatch(service.getAll(RESTAURANT_ID + 1), sortByPrice(Arrays.asList(meal, meal1, meal2, meal3, meal11,
                meal22, meal33)));
    }

    @Test
    public void getWithRestaurant() {
        Meal meal = service.getWithRestaurant(MEAL_ID, RESTAURANT_ID+1);
        assertMatch(meal, meal1);
        RestaurantTestData.assertMatch(meal.getRestaurant(), rest2);
    }
}