package votesystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import votesystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository.findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }

    @Override
    public List<Meal> getAllByDate(int restaurantId, LocalDate chosenDate) {
        return crudMealRepository.getAllByDate(restaurantId, chosenDate);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return crudMealRepository.getWithRestaurant(id, restaurantId);
    }
}
