package votesystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import votesystem.AuthorizedUser;
import votesystem.model.Restaurant;
import votesystem.model.User;
import votesystem.service.RestaurantService;
import votesystem.service.UserService;

import java.util.List;
import java.util.Objects;

import static votesystem.util.ValidationUtil.assureIdConsistent;
import static votesystem.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestaurantService service;

    @Autowired
    private UserService userService;


    public Restaurant get(int id) {
        log.info("get restaurant with id={}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant with id={}", id);
        service.delete(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {} with id={}", restaurant, id);
        service.update(restaurant);
    }

    public void voteForIt(int id) {
        log.info("vote for restaurant with id={}", id);
        User user = userService.get(AuthorizedUser.id());
        Restaurant userRestaurant = user.getRestaurant();
        Integer votedRestaurantId;

        if (userRestaurant != null) { //если он уже голосовал
            votedRestaurantId = userRestaurant.getId();
            if (Objects.equals(id, votedRestaurantId)) { //голос за тот же ресторан
                service.voteForIt(votedRestaurantId, "already_voted");
            } else { // минус голос за предыдущий ресторан
                service.voteForIt(votedRestaurantId, "decrement");
            }
        }
        // если не голосовал
        service.voteForIt(id, "increment");
        user.setRestaurant(get(id));
        userService.update(user);
    }

}
