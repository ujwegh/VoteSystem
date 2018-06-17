package votesystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import votesystem.AuthorizedUser;
import votesystem.model.Restaurant;
import votesystem.model.User;
import votesystem.service.RestaurantService;
import votesystem.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static votesystem.VoteAction.*;
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

    public void vote(int id) {
        log.info("vote for restaurant with id={}", id);
        User user = userService.get(AuthorizedUser.id()); //получаем юзера
        Restaurant userRestaurant = user.getRestaurant(); // получаем ресторан у юзера
        if (user.getVotingDate() == null || user.getVotingDate().isBefore(LocalDate.now())) {
            service.vote(id, userRestaurant, DID_NOT_VOTE);    // если не голосовал сегодня
        } else if (user.getVotingDate().isEqual(LocalDate.now())) {
            service.vote(id, userRestaurant, VOTED);    // если голосовал сегодня
        }
        user.setVotingDate(LocalDate.now());
        user.setRestaurant(get(id));
        userService.update(user);
    }

}
