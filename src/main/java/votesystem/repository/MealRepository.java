package votesystem.repository;

import votesystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getAll(int restaurantId);

    List<Meal> getAllByDate(int restaurantId, LocalDate chosenDate);

    default Meal getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
