package votesystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import votesystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Meal save(Meal item);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId ORDER BY m.price ASC ")
    List<Meal> getAll(@Param("restaurantId") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Meal m WHERE m.restaurant.id=:restaurantId AND m.date=:chosenDate ORDER BY m.price ASC")
    List<Meal> getAllByDate(@Param("restaurantId") int restaurantId, @Param("chosenDate") LocalDate chosenDate);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id, int restaurantId);

}
