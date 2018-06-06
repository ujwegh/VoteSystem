package votesystem.service;

import votesystem.model.Meal;
import votesystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    Meal get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Meal> getAll(int restaurantId);

    List<Meal> getAllByDate(int restaurantId, LocalDate chosenDate);

    Meal create(Meal meal, int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);

}
