package votesystem.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import votesystem.model.Meal;
import votesystem.service.MealService;

import java.time.LocalDate;
import java.util.List;

import static votesystem.util.ValidationUtil.assureIdConsistent;
import static votesystem.util.ValidationUtil.checkNew;

public class AbstractMealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal get(int id, int restaurantId) {
        log.info("get meal {} for user {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete meal {} for user {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public List<Meal> getAll(int restaurantId) {
        log.info("getAll for user {}", restaurantId);
        return service.getAll(restaurantId);
    }

    List<Meal> getAllByDate(int restaurantId, LocalDate chosenDate) {
        log.info("getAllByDate for date {}, for  {}", chosenDate);
        LocalDate date;
        if (chosenDate == null)
            date = LocalDate.now();
        else date = chosenDate;
        return service.getAllByDate(restaurantId, date);
    }

    public Meal create(Meal meal, int restaurantId) {
        checkNew(meal);
        log.info("create {} for user {}", meal, restaurantId);
        return service.create(meal, restaurantId);
    }

}
