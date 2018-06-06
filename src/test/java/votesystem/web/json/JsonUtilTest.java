package votesystem.web.json;

import org.junit.Assert;
import org.junit.Test;

import votesystem.UserTestData;
import votesystem.model.Meal;
import votesystem.model.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static votesystem.MealTestData.assertMatch;
import static votesystem.MealTestData.meal1;
import static votesystem.MealTestData.shashlikMeals;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(meal1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, meal1);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(shashlikMeals);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, shashlikMeals);
    }

    @Test
    public void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        Assert.assertEquals(user.getPassword(), "newPass");
    }
}