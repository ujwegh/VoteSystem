package votesystem.web.meal;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import votesystem.TestUtil;
import votesystem.model.Meal;
import votesystem.service.MealService;
import votesystem.web.AbstractControllerTest;
import votesystem.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static votesystem.MealTestData.*;
import static votesystem.RestaurantTestData.RESTAURANT_ID;
import static votesystem.TestUtil.*;
import static votesystem.UserTestData.ADMIN;
import static votesystem.web.restaurant.AdminRestaurantRestController.ADMIN_RESTAURANT_REST_URL;

public class AdminMealRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = ADMIN_RESTAURANT_REST_URL + "/" + RESTAURANT_ID + "/meals";

    @Autowired
    private MealService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + meal4.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT_ID), sortByPrice(Arrays.asList(meal5, meal6, meal41, meal52, meal63)));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + "/"+ 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/" + meal4.getId())
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(meal4));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + 1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(shashlikMeals));
    }

    @Test
    public void getAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/filter")
                .param("chosenDate", "2018-05-30")
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(contentJson(sortByPrice(Arrays.asList(meal41, meal52, meal63)
                )));
    }

    @Test
    public void testCreate() throws Exception {
        Meal created = new Meal( "Тушенка", 100, LocalDate.now());
        ResultActions actions = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));
        Meal returned = readFromJson(actions, Meal.class);
        created.setId(returned.getId());
        assertMatch(service.getAll(RESTAURANT_ID), sortByPrice(Arrays.asList(created, meal4, meal5, meal6, meal41,
                meal52, meal63)));
    }
}