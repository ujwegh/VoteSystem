package votesystem;

import votesystem.model.AbstractBaseEntity;
import votesystem.model.Meal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static votesystem.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 4;

    public static final Meal meal1 = new Meal(MEAL_ID, "Шаурма в лаваше", 150, LocalDate.now());
    public static final Meal meal2 = new Meal(MEAL_ID + 1, "Шаурма в батоне", 160, LocalDate.now());
    public static final Meal meal3 = new Meal(MEAL_ID + 2, "Шаурма в пите", 140, LocalDate.now());

    public static final Meal meal11 = new Meal(MEAL_ID+3, "Шаурма в лаваше", 150, LocalDate.of(2018,5,30));
    public static final Meal meal22 = new Meal(MEAL_ID + 4, "Шаурма в батоне", 160, LocalDate.of(2018,5,30));
    public static final Meal meal33 = new Meal(MEAL_ID + 5, "Шаурма в пите", 140, LocalDate.of(2018,5,30));

    public static final Meal meal4 = new Meal(MEAL_ID + 6, "Шашлык-машлык", 200, LocalDate.now());
    public static final Meal meal5 = new Meal(MEAL_ID + 7, "Шашлык из Пумбы", 300, LocalDate.now());
    public static final Meal meal6 = new Meal(MEAL_ID + 8, "Корейский народный шашлык", 250, LocalDate.now());

    public static final Meal meal41 = new Meal(MEAL_ID + 9, "Шашлык-машлык", 200, LocalDate.of(2018,5,30));
    public static final Meal meal52 = new Meal(MEAL_ID + 10, "Шашлык из Пумбы", 300, LocalDate.of(2018,5,30));
    public static final Meal meal63 = new Meal(MEAL_ID + 11, "Корейский народный шашлык", 250, LocalDate.of(2018,5,30));

    public static final List<Meal> shaurmaMeals = Arrays.asList(meal33, meal22, meal11, meal3, meal2, meal1);
    public static final List<Meal> shashlikMeals = Arrays.asList(meal63, meal52, meal41, meal6, meal5, meal4);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static List<Meal> sortByPrice(List<Meal> meals) {

        return meals.stream()
                .sorted(((Comparator<Meal>) (o1, o2) -> o1.getPrice() - o2.getPrice())
                        .thenComparingInt(AbstractBaseEntity::getId)).collect(Collectors.toList());
    }

    public static List<Meal> getByDate(List<Meal> meals, LocalDate localDate) {
        return meals.stream().filter(meal -> meal.getDate()==localDate).collect(Collectors.toList());
    }

}
