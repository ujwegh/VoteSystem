package votesystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import votesystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.USER_RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantRestController {

    public static final String USER_RESTAURANT_REST_URL = "/rest/restaurants";

    @GetMapping(value = "/restOnly", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithMeals() {
        return super.getAllWithMeals();
    }

    @GetMapping(value = "/restOnly/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getWithMeals(@PathVariable("id") int id) {
        return super.getWithMeals(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void vote(@PathVariable("id") int id) {
        super.vote(id);
    }

}
