package votesystem.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import votesystem.TestUtil;
import votesystem.model.Restaurant;
import votesystem.service.RestaurantService;
import votesystem.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static votesystem.RestaurantTestData.*;
import static votesystem.TestUtil.userHttpBasic;

import static votesystem.UserTestData.USER;

public class UserRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestaurantRestController.USER_RESTAURANT_REST_URL + '/';

    @Autowired
    private RestaurantService service;


    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(rest1, rest2));
    }

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL+"/"+RESTAURANT_ID)
                .with(userHttpBasic(USER)))
                .andDo(print()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(rest1));
    }

    @Test
    public void testGetNotFound() throws Exception { // not found restaurant for USER
        mockMvc.perform(get(REST_URL+1)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testVoteForIt() throws Exception {
        Restaurant votedRest = new Restaurant(rest1);
        votedRest.setVoteCount(1);
        if (LocalTime.now().isBefore(LocalTime.of(11,0))) {
            mockMvc.perform(put(REST_URL+RESTAURANT_ID)
                    .with(userHttpBasic(USER)))
                    .andDo(print())
                    .andExpect(status().isOk());
            assertMatch(service.get(RESTAURANT_ID), votedRest);
        } else {
            mockMvc.perform(put(REST_URL+RESTAURANT_ID)
                    .with(userHttpBasic(USER)))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity());
        }
    }
}