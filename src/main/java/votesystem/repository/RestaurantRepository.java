package votesystem.repository;

import votesystem.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    default Restaurant getWithMeals(int id){
        throw new UnsupportedOperationException();
    }

    Restaurant getByName(String name);
}
