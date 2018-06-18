package votesystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import votesystem.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository{
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name","voteCount");

    private final CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public DataJpaRestaurantRepositoryImpl(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }

    @Override
    public List<Restaurant> getAllWithMeals() {
        return crudRestaurantRepository.getAllWithMeals(SORT_NAME);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return crudRestaurantRepository.getWithMeals(id);
    }

    @Override
    public Restaurant getByName(String name) {
        return crudRestaurantRepository.getByName(name);
    }
}
