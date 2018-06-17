package votesystem.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "restaurant")
    @CollectionTable(name = "meals", joinColumns = @JoinColumn(name = "restaurant_id"))
    @OrderBy("price ASC")
    private List<Meal> meals;

    @Column(name = "vote_count", nullable = false)
    @NotNull
    private int voteCount;

    public Restaurant() {
        voteCount = 0;
    }

    public Restaurant(Integer id, String name, Meal... meals) {
        this(id, name, Arrays.asList(meals));
    }

    public Restaurant(Integer id, String name, List<Meal> meals) {
        super(id, name);
        this.meals = meals;
        voteCount = 0;
    }

    public Restaurant(Restaurant rest1) {
        this(rest1.getId(), rest1.getName(), rest1.getMeals());
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public @NotNull int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name +
                ", voteCount=" + voteCount + '\'' +
                '}';
    }


}
