package votesystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import votesystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.USER_RESTAURANT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantRestController {

    public static final String USER_RESTAURANT_REST_URL = "/rest/restaurants";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void vote(@PathVariable("id") int id) {
        super.vote(id);
    }
}
