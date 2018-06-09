package votesystem.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import votesystem.model.Meal;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static votesystem.web.restaurant.AdminRestaurantRestController.ADMIN_RESTAURANT_REST_URL;

@RestController
@RequestMapping(path = AdminMealRestController.ADMIN_MEAL_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMealRestController extends AbstractMealRestController{

    static final String ADMIN_MEAL_REST_URL = ADMIN_RESTAURANT_REST_URL + "/{restaurantId}/meals";

    @GetMapping("/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @GetMapping
    public List<Meal> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping(value = "/filter")
    List<Meal> getAllByDate(@PathVariable("restaurantId") int restaurantId,
                            @RequestParam(value = "chosenDate", required = false) LocalDate chosenDate) {
        return super.getAllByDate(restaurantId, chosenDate);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Valid @RequestBody Meal meal, @PathVariable("restaurantId") int
            restaurantId) {
        Meal created = super.create(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_RESTAURANT_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
