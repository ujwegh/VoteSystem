package votesystem.web.meal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import votesystem.model.Meal;
import votesystem.service.MealService;

import java.util.List;

import static votesystem.web.restaurant.UserRestaurantRestController.USER_RESTAURANT_REST_URL;

@RestController
@RequestMapping(path = UserMealRestController.USER_MEAL_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMealRestController extends AbstractMealRestController {

    static final String USER_MEAL_REST_URL = USER_RESTAURANT_REST_URL+"/{restaurantId}/meals";

    @GetMapping("/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @GetMapping
    public List<Meal> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }
}
