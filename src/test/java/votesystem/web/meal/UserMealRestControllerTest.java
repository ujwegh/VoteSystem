package votesystem.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import votesystem.TestUtil;
import votesystem.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static votesystem.MealTestData.*;
import static votesystem.RestaurantTestData.RESTAURANT_ID;
import static votesystem.TestUtil.contentJson;
import static votesystem.TestUtil.userHttpBasic;
import static votesystem.UserTestData.USER;
import static votesystem.web.restaurant.UserRestaurantRestController.USER_RESTAURANT_REST_URL;

public class UserMealRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(USER_RESTAURANT_REST_URL + "/" + RESTAURANT_ID + "/meals/" + 10010)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(meal4));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(USER_RESTAURANT_REST_URL + "/" + RESTAURANT_ID + "/meals/" + 1)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(USER_RESTAURANT_REST_URL + "/" + RESTAURANT_ID + "/meals")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(shashlikMeals));
    }
}