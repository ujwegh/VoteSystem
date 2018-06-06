package votesystem.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import votesystem.TestUtil;
import votesystem.model.Restaurant;
import votesystem.service.RestaurantService;
import votesystem.web.AbstractControllerTest;
import votesystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static votesystem.RestaurantTestData.*;
import static votesystem.TestUtil.readFromJson;
import static votesystem.TestUtil.userHttpBasic;
import static votesystem.UserTestData.ADMIN;

public class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String ADMIN_REST_URL = AdminRestaurantRestController.ADMIN_RESTAURANT_REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL+RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), rest2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(ADMIN_REST_URL+1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = new Restaurant(null, "Элитная харчевня");
        ResultActions actions = mockMvc.perform(post(ADMIN_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));
        Restaurant returned = readFromJson(actions, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(service.getAll(), rest2, rest1, created);
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(ADMIN_REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andDo(print()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(rest1, rest2));
    }

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(ADMIN_REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(rest1));
    }

    @Test
    public void testGetNotFound() throws Exception { // not found restaurant for USER
        mockMvc.perform(get(ADMIN_REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(rest1);
        updated.setName("Забегаловка");
        mockMvc.perform(put(ADMIN_REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        assertMatch(service.get(RESTAURANT_ID), updated);
    }
}