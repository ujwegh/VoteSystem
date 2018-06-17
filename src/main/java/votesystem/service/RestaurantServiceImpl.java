package votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import votesystem.model.Restaurant;
import votesystem.repository.RestaurantRepository;
import votesystem.VoteAction;
import votesystem.util.exception.AlreadyVotedException;
import votesystem.util.exception.NotFoundException;
import votesystem.util.exception.TooLateForVoteException;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static votesystem.util.ValidationUtil.checkNotFound;
import static votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return checkNotFoundWithId(repository.getWithMeals(id), id);
    }

    @Override
    public Restaurant getByName(String name) throws NotFoundException {
        Assert.notNull(name, "email must not be null");
        return checkNotFound(repository.getByName(name), "name=" + name);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    @Transactional
    public void vote(int id, Restaurant votedRestaurant, VoteAction action) throws NotFoundException, TooLateForVoteException, AlreadyVotedException {
        Restaurant restaurant = get(id);
        Assert.notNull(restaurant, "restaurant must not be null");
        Integer votedRestaurantId = null;
        if (votedRestaurant != null) {
            votedRestaurantId = votedRestaurant.getId();
        }
        LocalTime localTime = LocalTime.now();
        int votes_1 = restaurant.getVoteCount();
//        int votes_2 = votedRestaurant.getVoteCount();

        if (action.equals(VoteAction.VOTED)) { // если сегодня уже голосовал
            if (localTime.isBefore(LocalTime.of(11, 0))) {
                if (Objects.equals(id, votedRestaurantId)) { // если id ресторанов совпадают
                    throw new AlreadyVotedException("You have been already voted for this restaurant");
                } else { // если id ресторанов не совпадают
                    restaurant.setVoteCount(votes_1 + 1);
                    votedRestaurant.setVoteCount(votedRestaurant.getVoteCount() - 1);
                    repository.save(votedRestaurant);
                }
            } else {
                if (Objects.equals(id, votedRestaurantId)) {
                    throw new AlreadyVotedException("You have been already voted for this restaurant");
                }else throw new TooLateForVoteException("It is too late, vote can't be changed!");
            }
        } else { // если уже голосовал, но не сегодня или вообще не голосовал
            restaurant.setVoteCount(votes_1 + 1);
        }

        repository.save(restaurant);
    }
}
