package votesystem.service;


import votesystem.model.Restaurant;
import votesystem.VoteAction;
import votesystem.util.exception.AlreadyVotedException;
import votesystem.util.exception.NotFoundException;
import votesystem.util.exception.TooLateForVoteException;

import java.util.LinkedHashSet;
import java.util.List;

public interface RestaurantService {

  Restaurant create(Restaurant restaurant);

  Restaurant update(Restaurant restaurant);

  Restaurant get(int id) throws NotFoundException;

  void delete(int id) throws NotFoundException;

  List<Restaurant> getAll();

  List<Restaurant> getAllWithMeals();

  Restaurant getWithMeals(int id);

  Restaurant getByName(String name) throws NotFoundException;

  void vote(int id, Restaurant userRestaurant, VoteAction action) throws NotFoundException, TooLateForVoteException, AlreadyVotedException;
}
