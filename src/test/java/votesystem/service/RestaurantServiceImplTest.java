package votesystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import votesystem.MealTestData;
import votesystem.model.Restaurant;
import votesystem.util.exception.NotFoundException;
import votesystem.util.exception.TooLateForVoteException;

import java.util.List;

import static votesystem.MealTestData.*;
import static votesystem.RestaurantTestData.*;


public class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "столовка");
        Restaurant created = service.create(newRestaurant);

        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), rest2, rest1, newRestaurant);
    }

    @Test
    public void get() throws Exception {
        Restaurant rest = service.get(RESTAURANT_ID + 1);
        assertMatch(rest, rest2);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(rest1);
        updated.setName("Новая чебуречная");
        updated.setMeals(shaurmaMeals);
        service.update(updated);
        assertMatch(service.get(RESTAURANT_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID);
        assertMatch(service.getAll(), rest2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> restaurants = service.getAll();
        assertMatch(restaurants, rest2, rest1);
    }

    @Test
    public void getWithMeals() throws Exception {
        Restaurant restaurant = service.getWithMeals(RESTAURANT_ID);
        assertMatch(restaurant, rest1);
        MealTestData.assertMatch(restaurant.getMeals(), sortByPrice(shashlikMeals));
    }

    @Test
    public void getByName() throws Exception {
        Restaurant restaurant = service.getByName("Шашлычная №1");
        assertMatch(restaurant, rest1);
    }

    @Test
    public void voteForIt() throws Exception {
        Restaurant restaurant = new Restaurant(rest1);
        restaurant.setVoteCount(1);
        try {
            service.voteForIt(RESTAURANT_ID, "increment");
        } catch (TooLateForVoteException e) {
            restaurant.setVoteCount(0);
        }
        assertMatch(service.get(RESTAURANT_ID), restaurant);
    }
}