package votesystem;

import org.springframework.test.web.servlet.ResultMatcher;
import votesystem.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static votesystem.MealTestData.*;
import static votesystem.model.AbstractBaseEntity.START_SEQ;
import static votesystem.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Restaurant rest1 = new Restaurant(RESTAURANT_ID, "Шашлычная №1", shashlikMeals);
    public static final Restaurant rest2 = new Restaurant(RESTAURANT_ID + 1, "Шаурма ВАХ-какой вкусный!", shaurmaMeals);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "meals");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("meals").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "meals"));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "meals"));
    }
}
