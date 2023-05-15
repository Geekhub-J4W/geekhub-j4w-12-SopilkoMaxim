package edu.geekhub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.geekhub.Entity.User;
import edu.geekhub.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRegisterNewUser() throws Exception {
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", "jane.doe@example.com");
        userData.put("password", "password");
        userData.put("full-name", "Jane Doe");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userData)))
                .andExpect(status().isSeeOther())
                .andExpect(header().string(HttpHeaders.LOCATION, "/login.html"));

        verify(userService).addUser(any(User.class));
    }


    @Test
    @WithMockUser
    public void testGetSortedUserRatings() throws Exception {
        Map<String, Float> ratings = new HashMap<>();
        ratings.put("Tor", 4.5f);
        ratings.put("Loky", 3.5f);
        ratings.put("Halk", 2.5f);

        when(userService.getSortedUserRatings()).thenReturn(ratings);

        mockMvc.perform(get("/ratings"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)));

        verify(userService).getSortedUserRatings();
    }

    @Test
    @WithMockUser
    public void testUpdateRating() throws Exception {
        User user = new User("Tor", "tor@example.com", "password");
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/update-rating"))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateRating(user);
    }


    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(
                new User("Tor", "tor@gmail.com", "password"),
                new User("Loky", "loky@gmail.com", "password")
        );
        given(userService.getAllUsers()).willReturn(users);

        mockMvc.perform(get("/admin/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));


        verify(userService).getAllUsers();
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void testGetUser() throws Exception {
        User user = new User("Tor", "tor@gmail.com", "password");
        given(userService.getUserById(1)).willReturn(user);

       mockMvc.perform(get("/admin/users/1"))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(10)));

        verify(userService).getUserById(1);

    }

}